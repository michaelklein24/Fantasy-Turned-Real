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
    private String questionSequence;

    public CorrectAnswerId() {
    }

    public CorrectAnswerId(String surveyId, String questionSequence) {
        this.surveyId = surveyId;
        this.questionSequence = questionSequence;
    }
}
