package com.kleintwins.ftr.league.dto;

import com.kleintwins.ftr.show.dto.SeasonResponseBuilder;
import com.kleintwins.ftr.show.dto.Season;
import com.kleintwins.ftr.league.model.LeagueModel;
import com.kleintwins.ftr.league.model.ParticipantModel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

        Season season = SeasonResponseBuilder.buildSeason(leagueModel.getSeason());

        return League.builder()
                .leagueId(leagueModel.getLeagueId())
                .name(leagueModel.getName())
                .participants(participants)
                .season(season)
                .createTime(leagueModel.getCreateTime())
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
