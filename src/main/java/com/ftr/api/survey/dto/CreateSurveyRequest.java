package com.ftr.api.survey.dto;

import lombok.Data;

@Data
public class CreateSurveyRequest {
    private Integer leagueId;
    private Integer episodeId;
    private SurveyDetails surveyDetails;
}
