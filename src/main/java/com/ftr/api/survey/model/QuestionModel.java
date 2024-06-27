package com.ftr.api.survey.model;

import com.ftr.api.survey.code.QuestionTypeCode;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "lge_survey_question")
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_id", referencedColumnName = "surveyId")
    private SurveyModel surveyModel;

    private String question;

    private QuestionTypeCode questionType;

    private BigDecimal points;

    private String correctAnswer;
}
