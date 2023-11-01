package com.company.fantasyturnedreal.model.season;

import com.company.fantasyturnedreal.model.contestant.Contestant;
import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import com.company.fantasyturnedreal.model.league.League;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "season")
@EqualsAndHashCode(exclude = "leagues")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String seasonId;
    private String title;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    @JsonManagedReference("season-episodes")
    private List<Episode> episodes;

    @ManyToMany
    @JoinColumn(
            name = "season_contestant",
            referencedColumnName = "season_id"
    )
    private Set<Contestant> contestants;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    @JsonManagedReference("season-statuses")
    private Set<ContestantStatus> statuses;

    @JsonIgnore
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    @JsonBackReference("season-leagues")
    private Set<League> leagues;


}
