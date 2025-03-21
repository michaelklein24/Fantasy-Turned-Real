package com.kleintwins.ftr.league.dto;

import com.kleintwins.ftr.league.code.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String sequence;
    private String text;
    private QuestionType type;
    private int points;

    private List<AnswerOption> answerOptions;
    private List<ParticipantAnswer> participantAnswers;
    private AnswerOption correctAnswer;
}
