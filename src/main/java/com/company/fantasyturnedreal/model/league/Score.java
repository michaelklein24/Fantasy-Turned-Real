package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scoreId;

    private Integer totalPoints;
    private Integer pointsEarnedInEpisode;

    @ManyToOne
    @JsonBackReference("user-scores")
    private User user;

    @ManyToOne
    @JsonBackReference("league-scores")
    private League league;

    @ManyToOne
    @JsonBackReference("episode-scores")
    private Episode episode;

}
