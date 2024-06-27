package com.ftr.api.survey.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AnswerDetails {
    private Integer answerId;
    private String answer;
    private BigDecimal awardedPoints;
    private boolean correct;
}
