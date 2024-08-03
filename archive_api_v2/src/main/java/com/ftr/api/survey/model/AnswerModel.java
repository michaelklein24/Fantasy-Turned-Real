package com.ftr.api.survey.model;

import com.ftr.api.user.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lge_survey_answer")
public class AnswerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "questionId")
    private QuestionModel questionModel;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserModel userModel;

    private String answer;

    private boolean correct;
}
