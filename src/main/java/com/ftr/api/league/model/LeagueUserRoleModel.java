package com.ftr.api.league.model;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lge_user_role")
public class LeagueUserRoleModel {

    @EmbeddedId
    private LeagueUserId leagueUserId;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserModel userModel;

    @MapsId("leagueId")
    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "leagueId")
    private LeagueModel leagueModel;

    @Enumerated(value = EnumType.STRING)
    private LeagueRoleCode leagueRole;
}
