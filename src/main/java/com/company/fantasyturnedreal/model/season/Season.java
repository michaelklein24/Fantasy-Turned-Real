package com.company.fantasyturnedreal.model.season;

import com.company.fantasyturnedreal.model.contestant.Contestant;
import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import com.company.fantasyturnedreal.model.league.League;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "season")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String seasonId;
    private String title;
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Episode> episodes;

    @ManyToMany
    @JoinColumn(
            name = "season_contestant",
            referencedColumnName = "season_id"
    )
    private Set<Contestant> contestants;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<ContestantStatus> statuses;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<League> leagues;


}
