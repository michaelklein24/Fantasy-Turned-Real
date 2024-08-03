package com.ftr.api.league.model;

import com.ftr.api.user.model.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MapsId;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class LeagueUserId implements Serializable {
    private Integer userId;
    private Integer leagueId;

    public LeagueUserId() {}
    public LeagueUserId(Integer userId, Integer leagueId) {
        this.userId = userId;
        this.leagueId = leagueId;
    }
}
