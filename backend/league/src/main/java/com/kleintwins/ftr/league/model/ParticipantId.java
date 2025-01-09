package com.kleintwins.ftr.league.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ParticipantId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "league_id")
    private String leagueId;

    public ParticipantId() {}

    public ParticipantId(String userId, String leagueId) {
        this.userId = userId;
        this.leagueId = leagueId;
    }
}
