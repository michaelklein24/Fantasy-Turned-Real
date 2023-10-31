package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.enums.QuestionType;
import com.company.fantasyturnedreal.model.season.Episode;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    private Double points;
    @Enumerated
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;

}
