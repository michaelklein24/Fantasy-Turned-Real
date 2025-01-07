package com.kleintwins.ftr.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "lge_league")
@Entity
@Getter
@Setter
@ToString(exclude = "participants")
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LeagueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String leagueId;
    private String name;
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipantModel> participants;

    public LeagueModel() {}

    public LeagueModel(String leagueId, String name, List<ParticipantModel> participants) {
        this.leagueId = leagueId;
        this.name = name;
        this.participants = participants;
    }
}
