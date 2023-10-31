package com.company.fantasyturnedreal.model.season;

import com.company.fantasyturnedreal.model.league.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "episode")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long episode_id;

    private String title;
    private Date airDate;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL)
    private Set<Question> questions;
}
