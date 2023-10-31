package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.model.season.Season;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "league")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long league_id;

    private String league_name;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    private Set<Question> questions;
}
