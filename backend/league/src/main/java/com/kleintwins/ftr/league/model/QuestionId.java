package com.kleintwins.ftr.league.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class QuestionId {
    @Column(name = "survey_id", nullable = false)
    private String surveyId;

    @Column(name = "sequence", nullable = false)
    private String sequence;

}
