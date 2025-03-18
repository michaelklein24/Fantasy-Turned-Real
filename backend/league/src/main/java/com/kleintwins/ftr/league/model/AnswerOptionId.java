package com.kleintwins.ftr.league.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class AnswerOptionId {
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @Column(name = "survey_id", nullable = false)
    private String surveyId;

    @Column(name = "question_sequence", nullable = false)
    private Integer questionSequence;
}
