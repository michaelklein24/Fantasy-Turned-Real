package com.ftr.api.league.service;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.code.LeagueStatusCode;
import com.ftr.api.league.dao.LeagueDao;
import com.ftr.api.league.dao.LeagueUserRoleDao;
import com.ftr.api.league.dto.*;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.LeagueUserRoleModel;
import com.ftr.api.score.dao.ScoreDao;
import com.ftr.api.user.dao.UserDao;
import com.ftr.api.user.exception.UserNotPermittedException;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueDao leagueDao;
    private final UserDao userDao;
    private final LeagueUserRoleDao leagueUserRoleDao;
    private final ScoreDao scoreDao;

    public CreateLeagueResponse createLeague(MultipartFile image, CreateLeagueRequest createLeagueRequest, Integer userId) {
        LeagueModel leagueModel = mapCreateLeagueRequestToLeagueModel(createLeagueRequest);
        leagueModel = leagueDao.saveEntity(leagueModel);

        UserModel userModel = userDao.findEntityById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with userId '%d'", userId)));

        LeagueUserRoleModel leagueUserRoleModel = buildLeagueUserRoleModel(userModel, leagueModel);
        leagueUserRoleModel = leagueUserRoleDao.saveEntity(leagueUserRoleModel);

        return new CreateLeagueResponse();  // Populate this with necessary data
    }

    public GetLeagueSummariesForUser getLeagueSummariesForUser(Integer userId) {
        List<LeagueUserRoleModel> leagueUserRoleModels = leagueUserRoleDao.findLeagueUserRoleByUserId(userId);
        List<LeagueSummaryWithUserDetails> leagueSummaries = new ArrayList<>();

        for (LeagueUserRoleModel leagueUserRoleModel : leagueUserRoleModels) {
            LeagueModel leagueModel = leagueUserRoleModel.getLeagueModel();
            LeagueRoleCode leagueRole = leagueUserRoleDao.findLeagueUserRoleByUserIdAndLeagueId(userId, leagueModel.getLeagueId())
                    .orElseThrow(() -> new EntityNotFoundException("League User Role could not be found"))
                    .getLeagueRole();
            Integer totalNumberOfPlayers = leagueUserRoleDao.getTotalNumberOfLeagueUserRoleInLeague(leagueModel.getLeagueId());
            BigDecimal totalPoints = scoreDao.getTotalScoreForUserInLeague(userId, leagueModel.getLeagueId());
            Integer placement = scoreDao.getUserPlacementInLeague(userId, leagueModel.getLeagueId());

            leagueSummaries.add(buildLeagueSummaryWithUserDetails(leagueModel, leagueRole, totalPoints, placement, totalNumberOfPlayers));
        }

        return buildGetLeagueSummariesForUserResponse(leagueSummaries);
    }

    public GetLeagueDetailsByIdForUserResponse getLeagueDetailsByIdForUser(Integer userId, Integer leagueId) {
        boolean doesUserHaveAccessToLeague = leagueUserRoleDao.doesLeagueUserRoleExistInLeague(userId, leagueId);
        if (!doesUserHaveAccessToLeague) {
            throw new UserNotPermittedException(String.format("User with userId '%d' is not permitted to retrieve league details for league with leagueId '%d'.", userId, leagueId));
        }

        LeagueModel leagueModel = leagueDao.findEntityById(leagueId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("League with leagueId '%d' does not exist", leagueId)));

        List<LeagueUserRoleModel> leagueUserRoleModels = leagueUserRoleDao.findLeagueUserRoleByLeagueId(leagueId);
        List<LeagueUserSummary> leagueUserSummaries = new ArrayList<>();

        for (LeagueUserRoleModel leagueUserRoleModel : leagueUserRoleModels) {
            Integer leagueUserRoleUserId = leagueUserRoleModel.getUserModel().getUserId();
            Integer placement = scoreDao.getUserPlacementInLeague(leagueUserRoleUserId, leagueId);
            BigDecimal totalPoints = scoreDao.getTotalScoreForUserInLeague(leagueUserRoleUserId, leagueId);

            LeagueUserSummary leagueUserSummary = buildLeagueUserSummary(leagueUserRoleModel, placement, totalPoints);
            leagueUserSummaries.add(leagueUserSummary);
        }

        return buildGetLeagueDetailsByIdForUserResponse(leagueModel, leagueUserSummaries);
    }

    public void deleteLeague(Integer leagueId, Integer userId) {
        LeagueRoleCode role = leagueUserRoleDao.findLeagueUserRoleByUserIdAndLeagueId(userId, leagueId)
                .orElseThrow(() -> new EntityNotFoundException("League User Role is not found"))
                .getLeagueRole();
        if (!role.equals(LeagueRoleCode.ADMIN)) {
            throw new UserNotPermittedException(String.format("User with UserId '%d' is not allowed to delete league. Only ADMIN is allowed to delete.", userId));
        }

        leagueDao.deleteEntityById(leagueId);
    }

    private GetLeagueDetailsByIdForUserResponse buildGetLeagueDetailsByIdForUserResponse(LeagueModel leagueModel, List<LeagueUserSummary> leagueUserSummaries) {
        GetLeagueDetailsByIdForUserResponse response = new GetLeagueDetailsByIdForUserResponse();

        response.setName(leagueModel.getName());
        response.setStatus(leagueModel.getStatus());
        response.setLeagueUserSummaries(leagueUserSummaries);
        response.setAdmin(leagueUserSummaries.stream().filter(leagueUserSummary -> leagueUserSummary.getRole().equals(LeagueRoleCode.ADMIN)).findFirst().orElse(null));

        return response;
    }

    private LeagueUserSummary buildLeagueUserSummary(LeagueUserRoleModel leagueUserRoleModel, Integer placement, BigDecimal totalPoints) {
        LeagueUserSummary leagueUserSummary = new LeagueUserSummary();

        leagueUserSummary.setFirstName(leagueUserRoleModel.getUserModel().getFirstName());
        leagueUserSummary.setLastName(leagueUserRoleModel.getUserModel().getLastName());
        leagueUserSummary.setRole(leagueUserRoleModel.getLeagueRole());
        leagueUserSummary.setUserId(leagueUserRoleModel.getUserModel().getUserId());
        leagueUserSummary.setPlacement(placement);
        leagueUserSummary.setTotalPoints(totalPoints);

        return leagueUserSummary;
    }

    private LeagueModel mapCreateLeagueRequestToLeagueModel(CreateLeagueRequest request) {
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setName(request.getName());
        leagueModel.setStatus(LeagueStatusCode.NOT_STARTED);
        return leagueModel;
    }

    private LeagueUserRoleModel buildLeagueUserRoleModel(UserModel userModel, LeagueModel leagueModel) {
        LeagueUserRoleModel leagueUserRoleModel = new LeagueUserRoleModel();
        leagueUserRoleModel.setLeagueRole(LeagueRoleCode.ADMIN);
        leagueUserRoleModel.setUserModel(userModel);
        leagueUserRoleModel.setLeagueModel(leagueModel);
        return leagueUserRoleModel;
    }

    private GetLeagueSummariesForUser buildGetLeagueSummariesForUserResponse(List<LeagueSummaryWithUserDetails> leagueSummaries) {
        GetLeagueSummariesForUser response = new GetLeagueSummariesForUser();
        List<LeagueSummaryWithUserDetails> currentLeagues = leagueSummaries.stream()
                .filter(leagueSummary -> !leagueSummary.getStatus().equals(LeagueStatusCode.COMPLETED))
                .toList();
        List<LeagueSummaryWithUserDetails> completedLeagues = leagueSummaries.stream()
                .filter(leagueSummary -> leagueSummary.getStatus().equals(LeagueStatusCode.COMPLETED))
                .toList();
        response.setCurrentLeagues(currentLeagues);
        response.setCompletedLeagues(completedLeagues);
        return response;
    }

    private LeagueSummaryWithUserDetails buildLeagueSummaryWithUserDetails(LeagueModel leagueModel, LeagueRoleCode leagueRoleCode, BigDecimal totalPoints, Integer placement, Integer totalNumberOfPlayers) {
        LeagueSummaryWithUserDetails leagueSummaryWithUserDetails = new LeagueSummaryWithUserDetails();

        leagueSummaryWithUserDetails.setLeagueId(leagueModel.getLeagueId());
        leagueSummaryWithUserDetails.setName(leagueModel.getName());
        leagueSummaryWithUserDetails.setStatus(leagueModel.getStatus());
        leagueSummaryWithUserDetails.setRole(leagueRoleCode);
        leagueSummaryWithUserDetails.setTotalPoints(totalPoints);
        leagueSummaryWithUserDetails.setPlacement(placement);
        leagueSummaryWithUserDetails.setTotalNumberOfPlayers(totalNumberOfPlayers);

        return leagueSummaryWithUserDetails;
    }
}
