package com.kleintwins.ftr.controller.league;

import com.kleintwins.ftr.controller.league.dto.CreateLeagueResponse;
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
        List<Participant> participants = Optional.ofNullable(leagueModel.getParticipants())
                .orElse(Collections.emptyList())
                .stream()
                .map(participantModel -> Participant.builder()
                        .leagueId(participantModel.getParticipantId().getLeagueId())
                        .userId(participantModel.getParticipantId().getUserId())
                        .role(participantModel.getRole())
                        .build())
                .collect(Collectors.toList());

        League league = League.builder()
                .leagueId(leagueModel.getLeagueId())
                .name(leagueModel.getName())
                .participants(participants) // Updated to plural
                .build();

        return new CreateLeagueResponse(league);
    }
}
