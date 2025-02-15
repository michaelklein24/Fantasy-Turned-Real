package com.kleintwins.ftr.league.model;

import com.kleintwins.ftr.show.model.SeasonModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "lge_league")
@Entity
@Getter
@Setter
@ToString(exclude = "participants")
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LeagueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String leagueId;

    private String name;

    @OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
    private List<ParticipantModel> participants;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "season_sequence", referencedColumnName = "season_sequence"),
            @JoinColumn(name = "show", referencedColumnName = "show")
    })
    private SeasonModel season;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    private List<InviteModel> invites;


    public LeagueModel() {}

    public LeagueModel(String leagueId, String name, List<ParticipantModel> participants, SeasonModel season) {
        this.leagueId = leagueId;
        this.name = name;
        this.participants = participants;
        this.season = season;
    }

    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }
}
