package com.ftr.api.league.model;

import com.ftr.api.league.code.InviteStatusCode;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lge_invite")
public class InviteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inviteId;

    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "leagueId")
    private LeagueModel leagueModel;

    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    private InviteStatusCode status;
}
