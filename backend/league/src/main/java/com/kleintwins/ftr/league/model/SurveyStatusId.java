package com.kleintwins.ftr.league.model;

import com.kleintwins.ftr.league.code.SurveyStatus;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class SurveyStatusId implements Serializable {
    private String surveyId;
    @Enumerated(value = EnumType.STRING)
    private SurveyStatus status;

    public SurveyStatusId(String surveyId, SurveyStatus status) {
        this.surveyId = surveyId;
        this.status = status;
    }

    public SurveyStatusId() {
    }
}
