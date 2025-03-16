package com.kleintwins.ftr.league.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CorrectAnswerId {

    @Column(name = "survey_id", nullable = false)
    private String surveyId;

    @Column(name = "question_sequence", nullable = false)
    private Integer questionSequence;

    public CorrectAnswerId() {
    }

    public CorrectAnswerId(String surveyId, Integer questionSequence) {
        this.surveyId = surveyId;
        this.questionSequence = questionSequence;
    }
}
