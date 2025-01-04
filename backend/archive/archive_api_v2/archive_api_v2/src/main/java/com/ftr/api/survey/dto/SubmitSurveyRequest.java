package com.ftr.api.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmitSurveyRequest {
    private List<AnswerDto> answers;
}
