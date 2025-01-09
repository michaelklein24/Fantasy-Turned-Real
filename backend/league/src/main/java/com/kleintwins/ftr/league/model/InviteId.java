package com.kleintwins.ftr.league.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class InviteId implements Serializable {
    @Column(name = "league_id")
    private String leagueId;

    @Column(name = "invitee_user_id")
    private String inviteeUserId;

    public InviteId(String inviteeUserId, String leagueId) {
        this.inviteeUserId = inviteeUserId;
        this.leagueId = leagueId;
    }
}
