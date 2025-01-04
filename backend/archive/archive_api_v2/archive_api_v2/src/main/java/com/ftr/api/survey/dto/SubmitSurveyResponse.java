package com.ftr.api.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmitSurveyResponse {
    private List<AnswerDto> answers;
}
