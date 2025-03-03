package com.kleintwins.ftr.league.dto;

import lombok.Data;

@Data
public class GetSurveyByIdResponse {
    private Survey survey;

    public GetSurveyByIdResponse(Survey survey) {
        this.survey = survey;
    }

    public GetSurveyByIdResponse() {
    }
}
