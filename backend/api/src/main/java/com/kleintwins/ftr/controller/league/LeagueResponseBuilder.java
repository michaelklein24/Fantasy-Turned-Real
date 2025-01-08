package com.kleintwins.ftr.controller.league;

import com.kleintwins.ftr.controller.league.dto.CreateLeagueResponse;
import com.kleintwins.ftr.controller.league.dto.GetLeaguesForUserResponse;
import com.kleintwins.ftr.controller.league.dto.League;
import com.kleintwins.ftr.controller.league.dto.Participant;
import com.kleintwins.ftr.core.model.LeagueModel;
import com.kleintwins.ftr.core.model.ParticipantModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LeagueResponseBuilder {

    public static CreateLeagueResponse buildCreateLeagueResponse(LeagueModel leagueModel) {
        League league = LeagueResponseBuilder.buildLeague(leagueModel);
        return new CreateLeagueResponse(league);
    }

    public static GetLeaguesForUserResponse buildGetLeaguesForUserResponse(List<LeagueModel> leagueModels) {
        List<League> leagues = leagueModels.stream().map(LeagueResponseBuilder::buildLeague).toList();
        return new GetLeaguesForUserResponse(leagues);
    }

    public static League buildLeague(LeagueModel leagueModel) {
        List<Participant> participants = Optional.ofNullable(leagueModel.getParticipants())
                .orElse(Collections.emptyList())
                .stream()
                .map(LeagueResponseBuilder::buildParticipant)
                .toList();

        return League.builder()
                .leagueId(leagueModel.getLeagueId())
                .name(leagueModel.getName())
                .participants(participants)
                .build();
    }

    public static Participant buildParticipant(ParticipantModel participantModel) {
        return Participant.builder()
                .leagueId(participantModel.getParticipantId().getLeagueId())
                .userId(participantModel.getParticipantId().getUserId())
                .role(participantModel.getRole())
                .build();
    }
}
