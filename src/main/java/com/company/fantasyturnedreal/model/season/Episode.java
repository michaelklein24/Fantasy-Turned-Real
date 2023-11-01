package com.company.fantasyturnedreal.model.season;

import com.company.fantasyturnedreal.model.league.Question;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference("season-episodes")
    private Season season;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL)
    @JsonManagedReference("episode-questions")
    private Set<Question> questions;
}
