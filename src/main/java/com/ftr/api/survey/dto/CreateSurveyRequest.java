package com.ftr.api.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateSurveyRequest {
    private Integer leagueId;
    private Integer episodeId;
    private List<QuestionDetails> questions;
}
