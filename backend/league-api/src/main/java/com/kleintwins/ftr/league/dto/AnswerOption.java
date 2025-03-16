package com.kleintwins.ftr.league.dto;

import lombok.Data;

@Data
public class AnswerOption {
    private String sequence;
    private String questionSequence;
    private String surveyId;
    private String text;
}
