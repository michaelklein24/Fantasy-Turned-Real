package com.kleintwins.ftr.league.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InviteUserToLeagueResponse {
    private Invite invite;

    public InviteUserToLeagueResponse(Invite invite) {
        this.invite = invite;
    }

    public InviteUserToLeagueResponse() { }
}
