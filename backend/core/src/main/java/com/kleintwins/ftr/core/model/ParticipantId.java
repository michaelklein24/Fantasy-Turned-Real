package com.kleintwins.ftr.core.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ParticipantId implements Serializable {
    private String userId;
    private String leagueId;

    public ParticipantId() {}

    public ParticipantId(String userId, String leagueId) {
        this.userId = userId;
        this.leagueId = leagueId;
    }
}
