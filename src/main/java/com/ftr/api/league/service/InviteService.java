package com.ftr.api.league.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.league.code.InviteStatusCode;
import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.dao.InviteDao;
import com.ftr.api.league.dao.LeagueUserRoleDao;
import com.ftr.api.league.dto.GetPendingInvitesForUserResponse;
import com.ftr.api.league.dto.InviteDetails;
import com.ftr.api.league.dto.InviteEmailToLeagueRequest;
import com.ftr.api.league.dto.ResolveInviteRequest;
import com.ftr.api.league.model.InviteModel;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.LeagueUserRoleModel;
import com.ftr.api.security.exception.BadJwtException;
import com.ftr.api.user.dao.UserDao;
import com.ftr.api.user.exception.UserNotPermittedException;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InviteService extends AbstractService {

    private final InviteDao inviteDao;
    private final UserDao userDao;
    private final LeagueUserRoleDao leagueUserRoleDao;

    public GetPendingInvitesForUserResponse getInvitesForUser(Integer userId) {
        UserModel userModel = userDao.findEntityById(userId).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with userId '%d'", userId)));
        List<InviteModel> inviteModels = inviteDao.findInviteByEmailAndStatus(userModel.getEmail(), InviteStatusCode.PENDING);

        GetPendingInvitesForUserResponse getPendingInvitesForUserResponse = new GetPendingInvitesForUserResponse();
        List<InviteDetails> invites = new ArrayList<>();
        for (InviteModel inviteModel : inviteModels) {
            LeagueModel leagueModel = inviteModel.getLeagueModel();

            InviteDetails inviteDetails = new InviteDetails();
            inviteDetails.setLeagueId(leagueModel.getLeagueId());
            inviteDetails.setLeagueName(leagueModel.getName());
        }
        getPendingInvitesForUserResponse.setInvites(invites);
        return getPendingInvitesForUserResponse;
    }

    public void inviteUsersToLeague(InviteEmailToLeagueRequest inviteEmailToLeagueRequest, Integer userId) {
        LeagueUserRoleModel leagueUserRoleModel = leagueUserRoleDao.findLeagueUserRoleByUserIdAndLeagueId(userId, inviteEmailToLeagueRequest.getLeagueId())
                .orElseThrow(() -> new UserNotPermittedException(String.format("User with userId '%d' is not permitted to add userLeagueRole to league because they are not a participant in that league.", userId)));


        for (String email : inviteEmailToLeagueRequest.getEmails()) {
            // If an invite for a user already exists and has not been rejected, we want to add
            InviteModel inviteModel = inviteDao.findInviteByEmailAndStatusAndLeagueId(email, InviteStatusCode.PENDING, inviteEmailToLeagueRequest.getLeagueId()).orElse(new InviteModel());

            inviteModel.setEmail(email);
            inviteModel.setLeagueModel(leagueUserRoleModel.getLeagueModel());
            inviteModel.setStatus(InviteStatusCode.PENDING);

            if (inviteModel.getInviteId() == null) {
                inviteModel.setCreatedAt(LocalDateTime.now());
            }
            inviteDao.saveEntity(inviteModel);
        }
    }

    public void resolveInvite(ResolveInviteRequest resolveInviteRequest, Integer userId) {
        InviteModel inviteModel = inviteDao.findEntityById(resolveInviteRequest.getInviteId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Invite with inviteId '%d' was not found", resolveInviteRequest.getInviteId())));
        UserModel userModel = userDao.findEntityById(userId)
                .orElseThrow(() -> new BadJwtException(String.format("User with userId '%d' was not found", resolveInviteRequest.getInviteId())));

        boolean isInviteForUser = inviteModel.getEmail().equalsIgnoreCase(userModel.getEmail());
        if (!isInviteForUser) {
            throw new UserNotPermittedException(String.format("User with userId '%d' is not permitted to resolve invite with inviteId '%d'", userId, inviteModel.getInviteId()));
        }

        inviteModel.setUpdatedAt(LocalDateTime.now());
        inviteModel.setStatus(resolveInviteRequest.isAccepted() ? InviteStatusCode.ACCEPTED : InviteStatusCode.REJECTED);

        inviteDao.saveEntity(inviteModel);

        if (resolveInviteRequest.isAccepted()) {
            LeagueUserRoleModel leagueUserRoleModel = buildLeagueUserRoleModel(userModel, inviteModel.getLeagueModel());
            leagueUserRoleDao.saveEntity(leagueUserRoleModel);
        }
    }

    private LeagueUserRoleModel buildLeagueUserRoleModel(UserModel userModel, LeagueModel leagueModel) {
        LeagueUserRoleModel leagueUserRoleModel = new LeagueUserRoleModel();
        leagueUserRoleModel.setLeagueModel(leagueModel);
        leagueUserRoleModel.setLeagueRole(LeagueRoleCode.USER);
        leagueUserRoleModel.setUserModel(userModel);
        return leagueUserRoleModel;
    }
}
