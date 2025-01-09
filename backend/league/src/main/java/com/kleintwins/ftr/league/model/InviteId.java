package com.kleintwins.ftr.league.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class InviteId implements Serializable {
    @Column(name = "league_id")
    private String leagueId;

    @Column(name = "user_id")
    private String userId;
}
