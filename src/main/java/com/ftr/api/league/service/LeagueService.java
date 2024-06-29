package com.ftr.api.league.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.code.LeagueStatusCode;
import com.ftr.api.league.dto.*;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.league.repository.LeagueRepository;
import com.ftr.api.score.service.ScoreService;
import com.ftr.api.user.exception.UserNotPermittedException;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeagueService extends AbstractService implements IDaoImpl<LeagueModel> {

    private final LeagueRepository leagueRepository;
    private final ParticipantService participantService;
    private final ScoreService scoreService;
    private final UserService userService;

    public GetLeagueSummariesForUser getLeagueSummariesForUser(Integer userId) {
        GetLeagueSummariesForUser response = new GetLeagueSummariesForUser();

        List<LeagueModel> leagues = participantService.getLeaguesForParticipant(userId);
        List<LeagueSummaryWithUserDetails> currentLeagues = leagues.stream()
                .filter(LeagueModel::isNotCompleted)
                .map(league -> createLeagueSummaryWithUserDetails(league, userId))
                .toList();
        List<LeagueSummaryWithUserDetails> completedLeagues = leagues.stream()
                .filter(LeagueModel::isCompleted)
                .map(league -> createLeagueSummaryWithUserDetails(league, userId))
                .toList();
        response.setCurrentLeagues(currentLeagues);
        response.setCompletedLeagues(completedLeagues);

        return response;
    }

    private LeagueSummaryWithUserDetails createLeagueSummaryWithUserDetails(LeagueModel league, Integer userId) {
        LeagueSummaryWithUserDetails leagueSummaryWithUserDetails = new LeagueSummaryWithUserDetails();
        leagueSummaryWithUserDetails.setUserId(userId);
        leagueSummaryWithUserDetails.setLeagueId(league.getLeagueId());
        leagueSummaryWithUserDetails.setName(league.getName());
        leagueSummaryWithUserDetails.setStatus(league.getStatus());

        LeagueRoleCode leagueRole = participantService.getUserParticipantRoleForLeague(userId, league.getLeagueId());
        leagueSummaryWithUserDetails.setRole(leagueRole);

        BigDecimal totalPoints = scoreService.getTotalScoreForUserInLeague(userId, league.getLeagueId());
        leagueSummaryWithUserDetails.setTotalPoints(totalPoints);

        Integer placement = scoreService.getCurrentPlacementForUserInLeague(userId, league.getLeagueId());
        leagueSummaryWithUserDetails.setPlacement(placement);

        Integer totalNumberOfPlayers = participantService.getTotalNumberOfParticipantsInLeague(league.getLeagueId());
        leagueSummaryWithUserDetails.setTotalNumberOfPlayers(totalNumberOfPlayers);

        return leagueSummaryWithUserDetails;
    }

    public GetLeagueDetailsByIdForUserResponse getLeagueDetailsByIdForUser(Integer userId, Integer leagueId) {
        boolean doesUserHaveAccessToLeague = participantService.doesParticipantWithUserIdExistInLeague(userId, leagueId);

        if (!doesUserHaveAccessToLeague) {
            throw new UserNotPermittedException(String.format("User with userId '%d' is not permitted to retrieve league details for league with leagueId '%d'.", userId, leagueId));
        }
        GetLeagueDetailsByIdForUserResponse response = new GetLeagueDetailsByIdForUserResponse();

        LeagueModel leagueModel = getLeagueByLeagueId(leagueId).orElseThrow(() -> new EntityNotFoundException(String.format("League with leagueId '%d' does not exist", leagueId)));
        response.setName(leagueModel.getName());
        response.setStatus(leagueModel.getStatus());

        List<ParticipantModel> participantModels = participantService.getParticipantsForLeague(leagueId);
        List<ParticipantSummary> participantSummaries = participantModels.stream().map(this::buildParticipantSummary).toList();

        response.setParticipants(participantSummaries);
        response.setAdmin(participantSummaries.stream().filter(participant -> participant.getRole().equals(LeagueRoleCode.ADMIN)).findFirst().orElse(null));

        return response;
    }

    public Optional<LeagueModel> getLeagueByLeagueId(Integer leagueId) {
        return leagueRepository.findById(leagueId);
    }

    private ParticipantSummary buildParticipantSummary(ParticipantModel participantModel) {
        ParticipantSummary participantSummary = new ParticipantSummary();
        participantSummary.setFirstName(participantSummary.getFirstName());
        participantSummary.setLastName(participantSummary.getLastName());
        participantSummary.setRole(participantSummary.getRole());

        Integer leagueId = participantModel.getLeagueModel().getLeagueId();
        Integer userId = participantModel.getUserModel().getUserId();
        participantSummary.setUserId(userId);

        Integer placement = scoreService.getCurrentPlacementForUserInLeague(userId, leagueId);
        participantSummary.setPlacement(placement);
        BigDecimal totalPoints = scoreService.getTotalScoreForUserInLeague(userId, leagueId);
        participantSummary.setTotalPoints(totalPoints);

        return participantSummary;
    }

    public void deleteLeague(Integer leagueId, Integer userId) {
        LeagueRoleCode role = participantService.getUserParticipantRoleForLeague(leagueId, userId);
        if (!role.equals(LeagueRoleCode.ADMIN)) {
            throw new UserNotPermittedException(String.format("User with UserId '%d' is not allowed to delete league.  Only ADMIN is allowed to delete.", userId));
        }
        leagueRepository.deleteById(leagueId);
    }


    @Override
    public LeagueModel saveEntity(LeagueModel entity) {
        return leagueRepository.save(entity);
    }

    @Override
    public Optional<LeagueModel> findEntityById(Integer id) {
        return leagueRepository.findById(id);
    }

    @Override
    public void updateEntity(LeagueModel entity) {
        leagueRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        leagueRepository.deleteById(id);
    }
}
