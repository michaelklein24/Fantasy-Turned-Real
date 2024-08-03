package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.enums.QuestionStatus;
import com.company.fantasyturnedreal.enums.QuestionType;
import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "answers")
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    private Integer points;

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    private String questionText;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private LocalDateTime timeSubmitted = LocalDateTime.now();
    private LocalDateTime timeUpdated;

    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    private QuestionStatus status = QuestionStatus.INACTIVE;

    private List<String> potentialAnswers;

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
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne
    @JsonManagedReference("user-questions")
    private User submitter;

}
