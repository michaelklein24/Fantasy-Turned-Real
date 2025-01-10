package com.kleintwins.ftr.league.util;

import com.kleintwins.ftr.auth.util.UserDtoBuilder;
import com.kleintwins.ftr.league.code.InviteStatus;
import com.kleintwins.ftr.league.dto.*;
import com.kleintwins.ftr.league.model.InviteModel;
import com.kleintwins.ftr.show.dto.SeasonResponseBuilder;
import com.kleintwins.ftr.show.dto.Season;
import com.kleintwins.ftr.league.model.LeagueModel;
import com.kleintwins.ftr.league.model.ParticipantModel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LeagueDtoBuilder {

    public static CreateLeagueResponse buildCreateLeagueResponse(LeagueModel leagueModel) {
        League league = LeagueDtoBuilder.buildLeague(leagueModel);
        return new CreateLeagueResponse(league);
    }

    public static GetLeaguesForUserResponse buildGetLeaguesForUserResponse(List<LeagueModel> leagueModels) {
        List<League> leagues = leagueModels.stream().map(LeagueDtoBuilder::buildLeague).toList();
        return new GetLeaguesForUserResponse(leagues);
    }

    public static InviteUserToLeagueResponse buildCreateLeagueInviteResponse(InviteModel inviteModel) {
        return new InviteUserToLeagueResponse(LeagueDtoBuilder.buildInvite(inviteModel));
    }

    public static GetInvitesForLeagueResponse buildGetInvitesForLeagueResponse(List<InviteModel> inviteModels) {
        GetInvitesForLeagueResponse response = new GetInvitesForLeagueResponse();
        for (InviteModel inviteModel : inviteModels) {
            Invite invite = LeagueDtoBuilder.buildInvite(inviteModel);
            if (InviteStatus.APPROVED.equals(inviteModel.getStatus())) {
                response.getApproved().add(invite);

            } else if (InviteStatus.PENDING.equals(inviteModel.getStatus())) {
                response.getPending().add(invite);

            } else if (InviteStatus.DECLINED.equals(inviteModel.getStatus())) {
                response.getDeclined().add(invite);
            }
        }
        return response;
    }

    public static League buildLeague(LeagueModel leagueModel) {
        List<Participant> participants = Optional.ofNullable(leagueModel.getParticipants())
                .orElse(Collections.emptyList())
                .stream()
                .map(LeagueDtoBuilder::buildParticipant)
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

    public static Invite buildInvite(InviteModel inviteModel) {
        return Invite.builder()
                .leagueId(inviteModel.getInviteId().getLeagueId())
                .invitee(UserDtoBuilder.buildUser(inviteModel.getInvitee()))
                .inviter(UserDtoBuilder.buildUser(inviteModel.getInviter()))
                .status(inviteModel.getStatus())
                .createTime(inviteModel.getCreateTime())
                .updateTime(inviteModel.getUpdateTime())
                .build();
    }
}
