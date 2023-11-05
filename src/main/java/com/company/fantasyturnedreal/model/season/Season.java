package com.company.fantasyturnedreal.model.season;

import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.model.contestant.Contestant;
import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import com.company.fantasyturnedreal.model.league.League;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "season")
@EqualsAndHashCode(exclude = "leagues")
@ToString(exclude = {"leagues", "statuses", "contestants", "episodes"})
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seasonId;
    private String title;
    private Long seasonNumber;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    @JsonManagedReference("season-episodes")
    private Set<Episode> episodes = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Show show;

    @ManyToMany(mappedBy = "seasons", cascade = CascadeType.REMOVE)
    private Set<Contestant> contestants = new HashSet<>();

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    @JsonBackReference("season-statuses")
    private Set<ContestantStatus> statuses = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    @JsonBackReference("season-leagues")
    private Set<League> leagues = new HashSet<>();

}
