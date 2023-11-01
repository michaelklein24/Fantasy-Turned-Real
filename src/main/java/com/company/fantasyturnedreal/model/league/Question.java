package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.enums.QuestionType;
import com.company.fantasyturnedreal.model.season.Episode;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "answers")
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    private Double points;
    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    @JsonBackReference("episode-questions")
    private Episode episode;

    @ManyToOne
    @JoinColumn(name = "league_id")
    @JsonBackReference("league-questions")
    private League league;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference("question-answers")
    private Set<Answer> answers;

}
