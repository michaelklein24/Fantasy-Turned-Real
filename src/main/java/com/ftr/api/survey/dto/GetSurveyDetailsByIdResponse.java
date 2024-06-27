package com.ftr.api.survey.dto;

import lombok.Data;

@Data
public class GetSurveyDetailsByIdResponse {
    private SurveyDetails surveyDetails;
    private ScoringDetails scoringDetails;
}
