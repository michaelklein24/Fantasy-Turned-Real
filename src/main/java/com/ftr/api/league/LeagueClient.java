package com.ftr.api.league;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.code.LeagueStatusCode;
import com.ftr.api.league.dto.CreateLeagueRequest;
import com.ftr.api.league.dto.CreateLeagueResponse;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.league.service.LeagueService;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class LeagueClient {

    private final LeagueService leagueService;

    public CreateLeagueResponse createLeague(MultipartFile image, CreateLeagueRequest createLeagueRequest, Integer userId) {
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setName(createLeagueRequest.getName());
        leagueModel.setStatus(LeagueStatusCode.NOT_STARTED);

        leagueModel = leagueService.saveEntity(leagueModel);

        UserModel userModel = userService.findUserByUserId(userId).orElseThrow(() -> new EntityNotFoundException(String.format("Unabled to find user with userId '%d'", userId)));

        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setLeagueRole(LeagueRoleCode.ADMIN);
        participantModel.setUserModel(userModel);
        participantModel.setLeagueModel(leagueModel);
        participantModel = participantService.createParticipant(userModel, leagueModel, LeagueRoleCode.ADMIN);

        return new CreateLeagueResponse();
    }

}
