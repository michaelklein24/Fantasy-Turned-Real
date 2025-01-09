package com.kleintwins.ftr.league.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateLeagueInviteResponse {
    private Invite invite;

    public CreateLeagueInviteResponse(Invite invite) {
        this.invite = invite;
    }

    public CreateLeagueInviteResponse() { }
}
