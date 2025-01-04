package com.company.fantasyturnedreal.model.season;

import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import com.company.fantasyturnedreal.model.league.Question;
import com.company.fantasyturnedreal.model.league.Score;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "episode")
@ToString(exclude = {"contestantStatuses", "season", "questions", "scores"})
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long episodeId;

    private String title;
    private Date airDate;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.DETACH)
    @JsonBackReference("episode-statuses")
    private List<ContestantStatus> contestantStatuses;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonBackReference("season-episodes")
    private Season season;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL)
    @JsonManagedReference("episode-questions")
    private Set<Question> questions;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL)
    @JsonBackReference("episode-scores")
    private Set<Score> scores;
}
