package com.ftr.api.survey.dto;

import lombok.Data;

@Data
public class GetSurveyDetailsByIdResponse {
    private SurveyDto surveyDto;
    private ScoringSummaryDto scoringSummaryDto;
}
