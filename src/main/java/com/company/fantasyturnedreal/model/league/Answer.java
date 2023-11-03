package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"question", "user"})
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String answerId;

    private String answer;
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference("question-answers")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-answers")
    private User user;
}
