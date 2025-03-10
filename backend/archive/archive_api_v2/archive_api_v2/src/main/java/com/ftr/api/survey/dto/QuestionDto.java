package com.ftr.api.survey.dto;

import com.ftr.api.survey.code.QuestionTypeCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuestionDto {
    private Integer questionId;
    private Integer questionNumber;
    private String question;
    private QuestionTypeCode questionTypeCode;
    private String correctAnswer;
    private BigDecimal possiblePoints;
}
