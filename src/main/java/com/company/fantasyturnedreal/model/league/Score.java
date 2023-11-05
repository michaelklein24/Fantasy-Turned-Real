package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "score")
@EqualsAndHashCode(exclude = {"answer"})
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scoreId;

    private int pointsEarned;
//    private int pointsEarnedInEpisode;

    @OneToOne
    private Answer answer;

    @ManyToOne
    @JsonManagedReference("user-scores")
    private User user;

    @ManyToOne
    @JsonBackReference("league-scores")
    private League league;

    @ManyToOne
    @JsonBackReference("episode-scores")
    private Episode episode;

}
