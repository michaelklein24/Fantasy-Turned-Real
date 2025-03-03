package com.kleintwins.ftr.league.dto;

import lombok.Data;

@Data
public class CreateSurveyResponse {
    private Survey survey;

    public CreateSurveyResponse(Survey survey) {
        this.survey = survey;
    }

    public CreateSurveyResponse() {
    }
}
