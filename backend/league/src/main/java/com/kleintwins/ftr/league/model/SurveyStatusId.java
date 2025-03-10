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
    private SurveyStatus surveyStatus;

    public SurveyStatusId(String surveyId, SurveyStatus surveyStatus) {
        this.surveyId = surveyId;
        this.surveyStatus = surveyStatus;
    }

    public SurveyStatusId() {
    }
}
