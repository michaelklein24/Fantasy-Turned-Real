package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.model.season.Season;
import com.company.fantasyturnedreal.model.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "league")
@EqualsAndHashCode(exclude = {"season", "users"})
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leagueId;

    private String leagueName;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonManagedReference("season-leagues")
    private Season season;

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    @JsonManagedReference("league-questions")
    @ToString.Exclude
    private Set<Question> questions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "league_user",
            referencedColumnName = "league_id"
    )
    @JsonManagedReference("league-users-managed")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    @JsonManagedReference("league-scores")
    private Set<Score> scores;
}
