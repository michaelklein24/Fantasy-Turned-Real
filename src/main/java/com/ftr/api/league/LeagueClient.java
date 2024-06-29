package com.ftr.api.league;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.code.LeagueStatusCode;
import com.ftr.api.league.dto.*;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.league.service.LeagueDao;
import com.ftr.api.league.service.ParticipantService;
import com.ftr.api.score.service.ScoreService;
import com.ftr.api.user.exception.UserNotPermittedException;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LeagueClient {

    private final LeagueDao leagueDao;
    private final UserService userService;
    private final ParticipantService participantService;
    private final ScoreService scoreService;

    public CreateLeagueResponse createLeague(MultipartFile image, CreateLeagueRequest createLeagueRequest, Integer userId) {
        LeagueModel leagueModel = mapCreateLeagueRequestToLeagueModel(createLeagueRequest);
        leagueModel = leagueDao.saveEntity(leagueModel);

        UserModel userModel = userService.findEntityById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with userId '%d'", userId)));

        ParticipantModel participantModel = buildParticipantModel(userModel, leagueModel);
        participantModel = participantService.createParticipant(userModel, leagueModel, LeagueRoleCode.ADMIN);

        return new CreateLeagueResponse();  // Populate this with necessary data
    }

    public GetLeagueSummariesForUser getLeagueSummariesForUser(Integer userId) {
        List<LeagueModel> leagueModels = leagueDao.getLeaguesForUser(userId);
        List<LeagueSummaryWithUserDetails> leagueSummaries = new ArrayList<>();

        for (LeagueModel leagueModel : leagueModels) {
            LeagueRoleCode leagueRole = participantService.getUserParticipantRoleForLeague(userId, leagueModel.getLeagueId());
            BigDecimal totalPoints = scoreService.getTotalScoreForUserInLeague(userId, leagueModel.getLeagueId());
            Integer placement = scoreService.getCurrentPlacementForUserInLeague(userId, leagueModel.getLeagueId());
            Integer totalNumberOfPlayers = participantService.getTotalNumberOfParticipantsInLeague(leagueModel.getLeagueId());

            leagueSummaries.add(buildLeagueSummaryWithUserDetails(leagueModel, leagueRole, totalPoints, placement, totalNumberOfPlayers));
        }

        return buildGetLeagueSummariesForUserResponse(leagueSummaries);
    }

    public GetLeagueDetailsByIdForUserResponse getLeagueDetailsByIdForUser(Integer userId, Integer leagueId) {
        boolean doesUserHaveAccessToLeague = participantService.doesParticipantWithUserIdExistInLeague(userId, leagueId);
        if (!doesUserHaveAccessToLeague) {
            throw new UserNotPermittedException(String.format("User with userId '%d' is not permitted to retrieve league details for league with leagueId '%d'.", userId, leagueId));
        }

        LeagueModel leagueModel = leagueDao.findEntityById(leagueId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("League with leagueId '%d' does not exist", leagueId)));

        List<ParticipantModel> participantModels = participantService.getParticipantsForLeague(leagueId);
        List<ParticipantSummary> participantSummaries = new ArrayList<>();

        for (ParticipantModel participantModel : participantModels) {
            Integer participantsUserId = participantModel.getUserModel().getUserId();
            Integer placement = scoreService.getCurrentPlacementForUserInLeague(participantsUserId, leagueId);
            BigDecimal totalPoints = scoreService.getTotalScoreForUserInLeague(participantsUserId, leagueId);

            ParticipantSummary participantSummary = buildParticipantSummary(participantModel, placement, totalPoints);
            participantSummaries.add(participantSummary);
        }

        return buildGetLeagueDetailsByIdForUserResponse(leagueModel, participantSummaries);
    }

    public void deleteLeague(Integer leagueId, Integer userId) {
        LeagueRoleCode role = participantService.getUserParticipantRoleForLeague(leagueId, userId);
        if (!role.equals(LeagueRoleCode.ADMIN)) {
            throw new UserNotPermittedException(String.format("User with UserId '%d' is not allowed to delete league. Only ADMIN is allowed to delete.", userId));
        }

        leagueDao.deleteEntityById(leagueId);
    }

    private GetLeagueDetailsByIdForUserResponse buildGetLeagueDetailsByIdForUserResponse(LeagueModel leagueModel, List<ParticipantSummary> participantSummaries) {
        GetLeagueDetailsByIdForUserResponse response = new GetLeagueDetailsByIdForUserResponse();

        response.setName(leagueModel.getName());
        response.setStatus(leagueModel.getStatus());
        response.setParticipants(participantSummaries);
        response.setAdmin(participantSummaries.stream().filter(participant -> participant.getRole().equals(LeagueRoleCode.ADMIN)).findFirst().orElse(null));

        return response;
    }

    private ParticipantSummary buildParticipantSummary(ParticipantModel participantModel, Integer placement, BigDecimal totalPoints) {
        ParticipantSummary participantSummary = new ParticipantSummary();

        participantSummary.setFirstName(participantModel.getUserModel().getFirstName());
        participantSummary.setLastName(participantModel.getUserModel().getLastName());
        participantSummary.setRole(participantModel.getLeagueRole());
        participantSummary.setUserId(participantModel.getUserModel().getUserId());
        participantSummary.setPlacement(placement);
        participantSummary.setTotalPoints(totalPoints);

        return participantSummary;
    }

    private LeagueModel mapCreateLeagueRequestToLeagueModel(CreateLeagueRequest request) {
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setName(request.getName());
        leagueModel.setStatus(LeagueStatusCode.NOT_STARTED);
        return leagueModel;
    }

    private ParticipantModel buildParticipantModel(UserModel userModel, LeagueModel leagueModel) {
        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setLeagueRole(LeagueRoleCode.ADMIN);
        participantModel.setUserModel(userModel);
        participantModel.setLeagueModel(leagueModel);
        return participantModel;
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
