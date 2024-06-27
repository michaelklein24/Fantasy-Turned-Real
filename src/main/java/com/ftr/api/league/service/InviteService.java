package com.ftr.api.league.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.league.code.InviteStatusCode;
import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.dto.GetPendingInvitesForUserResponse;
import com.ftr.api.league.dto.InviteDetails;
import com.ftr.api.league.dto.InviteParticipantsToLeagueRequest;
import com.ftr.api.league.dto.ResolveInviteRequest;
import com.ftr.api.league.model.InviteModel;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.league.repository.InviteRepository;
import com.ftr.api.security.exception.BadJwtException;
import com.ftr.api.user.exception.UserNotPermittedException;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InviteService extends AbstractService {

    private final InviteRepository inviteRepository;
    private final UserService userService;
    private final ParticipantService participantService;

    public GetPendingInvitesForUserResponse getInvitesForUser(Integer userId) {
        UserModel userModel = userService.findUserByUserId(userId).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with userId '%d'", userId)));
        List<InviteModel> inviteModels = inviteRepository.findByEmailAndStatus(userModel.getEmail(), InviteStatusCode.PENDING);

        GetPendingInvitesForUserResponse getPendingInvitesForUserResponse = new GetPendingInvitesForUserResponse();
        List<InviteDetails> invites = new ArrayList<>();
        for (InviteModel inviteModel : inviteModels) {
            LeagueModel leagueModel = inviteModel.getLeagueModel();

            InviteDetails inviteDetails = new InviteDetails();
            inviteDetails.setLeagueId(leagueModel.getLeagueId());
            inviteDetails.setLeagueName(leagueModel.getName());
            inviteDetails.setInvitedBy(inviteModel.getInvitedByParticipant().getUserModel().getFirstName());
        }
        getPendingInvitesForUserResponse.setInvites(invites);
        return getPendingInvitesForUserResponse;
    }

    public void inviteUsersToLeague(InviteParticipantsToLeagueRequest inviteParticipantsToLeagueRequest, Integer userId) {
        ParticipantModel participantModel = participantService.getParticipantByUserIdAndLeagueId(userId, inviteParticipantsToLeagueRequest.getLeagueId())
                .orElseThrow(() -> new UserNotPermittedException(String.format("User with userId '%d' is not permitted to add participant to league because they are not a participant in that league.", userId)));


        for (String email : inviteParticipantsToLeagueRequest.getEmails()) {
            // If an invite for a user already exists and has not been rejected, we want to add
            InviteModel inviteModel = inviteRepository.findByEmailAndStatusAndLeagueModelLeagueId(email, InviteStatusCode.PENDING, inviteParticipantsToLeagueRequest.getLeagueId()).orElse(new InviteModel());

            inviteModel.setEmail(email);
            inviteModel.setInvitedByParticipant(participantModel);
            inviteModel.setLeagueModel(participantModel.getLeagueModel());
            inviteModel.setStatus(InviteStatusCode.PENDING);

            if (inviteModel.getInviteId() == null) {
                inviteModel.setCreatedAt(LocalDateTime.now());
            }
            inviteRepository.save(inviteModel);
        }
    }

    public void resolveInvite(ResolveInviteRequest resolveInviteRequest, Integer userId) {
        InviteModel inviteModel = inviteRepository.findById(resolveInviteRequest.getInviteId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Invite with inviteId '%d' was not found", resolveInviteRequest.getInviteId())));
        UserModel userModel = userService.findUserByUserId(userId)
                .orElseThrow(() -> new BadJwtException(String.format("User with userId '%d' was not found", resolveInviteRequest.getInviteId())));

        boolean isInviteForUser = inviteModel.getEmail().equalsIgnoreCase(userModel.getEmail());
        if (!isInviteForUser) {
            throw new UserNotPermittedException(String.format("User with userId '%d' is not permitted to resolve invite with inviteId '%d'", userId, inviteModel.getInviteId()));
        }

        inviteModel.setUpdatedAt(LocalDateTime.now());
        inviteModel.setStatus(resolveInviteRequest.isAccepted() ? InviteStatusCode.ACCEPTED : InviteStatusCode.REJECTED);

        inviteRepository.save(inviteModel);

        if (resolveInviteRequest.isAccepted()) {
            participantService.createParticipant(userModel, inviteModel.getLeagueModel(), LeagueRoleCode.USER);
        }
    }
}
