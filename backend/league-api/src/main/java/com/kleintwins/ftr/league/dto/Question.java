package com.kleintwins.ftr.league.dto;

import com.kleintwins.ftr.league.code.QuestionType;
import lombok.Data;

@Data
public class Question {
    private String sequence;
    private String text;
    private QuestionType type;
    private int points;
    // TODO: Finish Building Question and Answer DTOs
}
