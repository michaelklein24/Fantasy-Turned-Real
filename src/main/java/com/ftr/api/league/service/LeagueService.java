package com.ftr.api.league.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.code.LeagueStatusCode;
import com.ftr.api.league.dto.*;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.league.repository.LeagueRepository;
import com.ftr.api.score.service.ScoreService;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeagueService extends AbstractService {

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

        String image = getImageBase64(league.getImageData());
        leagueSummaryWithUserDetails.setImage(image);

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

    public CreateLeagueResponse createLeague(MultipartFile image, CreateLeagueRequest createLeagueRequest, Integer userId) {
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setName(createLeagueRequest.getName());
        leagueModel.setStatus(LeagueStatusCode.NOT_STARTED);
        try {
            leagueModel.setImageData(image.getBytes());
        } catch (IOException e) {
            log.error("Unable to save image!", e);
        }
        leagueModel = leagueRepository.save(leagueModel);

        UserModel userModel = userService.findUserByUserId(userId).orElseThrow(() -> new EntityNotFoundException(String.format("Unabled to find user with userId '%d'", userId)));

        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setLeagueRole(LeagueRoleCode.ADMIN);
        participantModel.setUserModel(userModel);
        participantModel.setLeagueModel(leagueModel);
        participantModel = participantService.createParticipant(userModel, leagueModel, LeagueRoleCode.ADMIN);

        return new CreateLeagueResponse();
    }

    public GetLeagueDetailsByIdForUserResponse getLeagueDetailsByIdForUser(Integer userId, Integer leagueId) {
        boolean doesUserHaveAccessToLeague = participantService.doesParticipantWithUserIdExistInLeague(userId, leagueId);

        if (!doesUserHaveAccessToLeague) {
            throw new RuntimeException("NOT_AUTHORIZED TO ACCESS LEAGUE DETAILS");
        }

        return null;
    }
}
