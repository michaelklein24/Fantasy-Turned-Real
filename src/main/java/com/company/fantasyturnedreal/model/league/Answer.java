package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(exclude = {"question", "user"})
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;

    private String answer;
    private Boolean isCorrect;

    private LocalDateTime timeSubmitted = LocalDateTime.now();
    private LocalDateTime timeUpdated;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference("question-answers")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-answers")
    private User user;

    @OneToOne(mappedBy = "answer", cascade = CascadeType.ALL)
    @JsonBackReference("answer-score")
    private Score score;
}
