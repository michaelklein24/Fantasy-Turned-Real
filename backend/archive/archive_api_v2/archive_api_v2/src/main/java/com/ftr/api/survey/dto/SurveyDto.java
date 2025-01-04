package com.ftr.api.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyDto {
    private Integer surveyId;
    private Integer episodeNumber;
    private String episodeTitle;
    private List<QuestionDto> questions;
    private List<AnswerDto> answers;
}
