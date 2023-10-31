package com.company.fantasyturnedreal.model.league;

import com.company.fantasyturnedreal.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
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
    private Question question;

    @ManyToOne
    @JoinColumn(name = "`user`")
    private User user;
}
