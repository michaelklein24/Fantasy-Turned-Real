package com.ftr.api.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyDetails {
    private Integer surveyId;
    private Integer episodeNumber;
    private String episodeTitle;
    private List<QuestionDetails> questionDetails;
}
