package com.kleintwins.ftr.league.service;

import com.kleintwins.ftr.core.exception.EntityNotFound;
import com.kleintwins.ftr.core.service.I18nService;
import com.kleintwins.ftr.league.code.InviteStatus;
import com.kleintwins.ftr.league.code.LeagueRole;
import com.kleintwins.ftr.league.code.SurveyType;
import com.kleintwins.ftr.league.exception.ParticipantAlreadyInLeague;
import com.kleintwins.ftr.league.exception.UserAlreadyInvited;
import com.kleintwins.ftr.league.model.*;
import com.kleintwins.ftr.league.repository.InviteRepository;
import com.kleintwins.ftr.league.repository.LeagueRepository;
import com.kleintwins.ftr.league.repository.ParticipantRepository;
import com.kleintwins.ftr.league.task.ILeagueCreationTask;
import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.notification.service.NotificationService;
import com.kleintwins.ftr.show.model.EpisodeModel;
import com.kleintwins.ftr.show.model.SeasonModel;
import com.kleintwins.ftr.show.service.SeasonService;
import com.kleintwins.ftr.user.model.UserModel;
import com.kleintwins.ftr.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeagueService {

    private final LeagueRepository leagueRepo;
    private final ParticipantRepository participantRepo;
    private final InviteRepository inviteRepo;

    private final I18nService i18nService;
    private final SeasonService seasonService;
    private final UserService userService;
    private final NotificationService notificationService;

    private final List<ILeagueCreationTask> leagueCreationTasks;

    @Transactional
    public LeagueModel createLeague(String name, String ownerId, String show, int seasonSequence) {
        SeasonModel seasonModel = seasonService.getSeasonByShowAndSeasonId(show, seasonSequence);

        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setName(name);
        leagueModel.setSeason(seasonModel);
        LeagueModel savedLeague = leagueRepo.save(leagueModel);

        ParticipantModel owner = addUserToLeague(savedLeague.getLeagueId(), ownerId, LeagueRole.OWNER);

        leagueModel.setParticipants(List.of(owner));

        for (ILeagueCreationTask leagueCreationTask : leagueCreationTasks)
            leagueCreationTask.doWork(leagueModel);

        return leagueModel;
    }

    public List<LeagueModel> getLeaguesForUser(String userId) {
        List<ParticipantModel> participantModels = participantRepo.findByParticipantIdUserId(userId);
        return participantModels.stream().map(ParticipantModel::getLeague).toList();
    }

    public List<InviteModel> getInvitesForLeague(String leagueId) {
        return inviteRepo.findByInviteIdLeagueId(leagueId);
    }

    public InviteModel createInvite(String inviteeEmail, String inviterUserId, String leagueId) {
        UserModel invitedUser = userService.findUserByEmail(inviteeEmail);
        UserModel inviterUser = userService.findUserByUserId(inviterUserId);

        LeagueModel leagueModel = getLeagueById(leagueId);

        InviteModel inviteModel;
        InviteId inviteId = new InviteId(invitedUser.getUserId(), leagueModel.getLeagueId());
        try {
            inviteModel = getInviteModelById(inviteId);
        } catch (EntityNotFound e) {
            inviteModel = null;
        }

        if (inviteModel != null && !InviteStatus.DECLINED.equals(inviteModel.getStatus())) {
            String message = i18nService.translate("api.league.createInvite.response.error.userAlreadyInvited.message", inviteeEmail);
            throw new UserAlreadyInvited(message);
        }

        inviteModel = InviteModel.builder()
                .inviteId(inviteId)
                .league(leagueModel)
                .invitee(invitedUser)
                .inviter(inviterUser)
                .status(InviteStatus.PENDING)
                .build();

        String[] args = {inviterUser.getFirstName(), leagueModel.getName()};
        notificationService.createNotification(invitedUser.getUserId(), leagueModel.getLeagueId(), NotificationReferenceType.LEAGUE_INVITE, args);

        return inviteRepo.save(inviteModel);
    }

    public InviteModel getInviteModelById(String inviteeUserId, String leagueId) {
        InviteId inviteId = new InviteId(inviteeUserId, leagueId);
        return getInviteModelById(inviteId);
    }

    public InviteModel getInviteModelById(InviteId inviteId) {
        return inviteRepo.findById(inviteId).orElseThrow(
                () -> {
                    String message = i18nService.translate("api.league.findInviteById.response.error.notFound.message", inviteId.getInviteeUserId(), inviteId.getLeagueId());
                    return new EntityNotFound(message);
                }
        );
    }

    public LeagueModel getLeagueById(String leagueId) {
        return leagueRepo.findById(leagueId).orElseThrow(
            () -> {
                String message = i18nService.translate(
                        "api.league.findLeagueById.response.error.notFound.message",
                        leagueId
                );
                return new EntityNotFound(message);
            });
    }

    @Transactional
    public void processInvite(String inviteeUserId, String leagueId, InviteStatus newStatus) {
        InviteModel inviteModel = getInviteModelById(inviteeUserId, leagueId);
        inviteModel.setStatus(newStatus);

        // Add user to league if invite is approved
        if (InviteStatus.APPROVED.equals(newStatus)) {
            addUserToLeague(leagueId, inviteeUserId, LeagueRole.MEMBER);
        }
        updateInviteNotification(leagueId, newStatus);
    }

    private void updateInviteNotification(String leagueId, InviteStatus newStatus) {
        try {
            NotificationModel notificationModel = notificationService.findLeagueInviteNotification(leagueId);
            if (notificationModel == null) {
                log.warn("Notification not found for league: {}", leagueId);
                return;
            }
            String completedActionLabel = determineCompletedActionLabel(newStatus);
            if (completedActionLabel != null) {
                notificationService.updateNotificationCompletedAction(notificationModel, completedActionLabel);
            }
        } catch (EntityNotFound ex) {
            log.error("Notification not found for league: {}", leagueId, ex);
        }
    }

    private String determineCompletedActionLabel(InviteStatus newStatus) {
        return switch (newStatus) {
            case APPROVED -> "Approved";
            case DECLINED -> "Declined";
            default -> null;
        };
    }

    public ParticipantModel addUserToLeague(String leagueId, String userId, LeagueRole leagueRole) {

        LeagueModel leagueModel = getLeagueById(leagueId);

        ParticipantModel participantModel;

        ParticipantId participantId = new ParticipantId(userId, leagueModel.getLeagueId());

        if (participantRepo.existsById(participantId)) {
            String message = i18nService.translate("api.league.existsParticipant.response.error.found.message", participantId.getUserId(), participantId.getLeagueId());
            throw new ParticipantAlreadyInLeague(message);
        }

        participantModel = new ParticipantModel();
        participantModel.setParticipantId(participantId);
        participantModel.setRole(leagueRole);
        participantModel.setLeague(leagueModel);
        participantModel.setUser(userService.findUserByUserId(userId));
        return participantRepo.save(participantModel);
    }

    public ParticipantModel getParticipantById(ParticipantId participantId) {
        return participantRepo.findById(participantId).orElseThrow(() -> {
            String message = i18nService.translate("api.league.findParticipantById.response.error.notFound.message", participantId.getUserId(), participantId.getLeagueId());
            return new EntityNotFound(message);
        });
    }

}
