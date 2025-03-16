package com.kleintwins.ftr.league.dto;

import com.kleintwins.ftr.league.code.AnswerOptionType;
import com.kleintwins.ftr.league.code.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionForSurveyRequest {
    private String text;
    private QuestionType type;
    private int points;
    private AnswerOptionType answerOptionType;
}
