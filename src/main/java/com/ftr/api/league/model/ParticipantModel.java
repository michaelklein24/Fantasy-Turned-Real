package com.ftr.api.league.model;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lge_participant")
public class ParticipantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer participantId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private LeagueModel leagueModel;

    @Enumerated(value = EnumType.STRING)
    private LeagueRoleCode leagueRole;
}
