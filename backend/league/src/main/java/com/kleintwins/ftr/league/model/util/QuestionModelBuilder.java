package com.kleintwins.ftr.league.model.util;

import com.kleintwins.ftr.league.model.AnswerOptionModel;
import com.kleintwins.ftr.league.model.CorrectAnswerModel;
import com.kleintwins.ftr.league.model.QuestionModel;
import com.kleintwins.ftr.league.model.SurveyModel;
import com.kleintwins.ftr.league.code.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class QuestionModelBuilder {

    private String text;
    private int points;
    private QuestionType questionType;
    private SurveyModel survey;
    private List<AnswerOptionModel> answerOptions = new ArrayList<>();
    private CorrectAnswerModel correctAnswer;

    public QuestionModelBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public QuestionModelBuilder withPoints(int points) {
        this.points = points;
        return this;
    }

    public QuestionModelBuilder withQuestionType(QuestionType questionType) {
        this.questionType = questionType;
        return this;
    }

    public QuestionModelBuilder withSurvey(SurveyModel survey) {
        this.survey = survey;
        return this;
    }

    public QuestionModelBuilder addAnswerOption(AnswerOptionModel answerOption) {
        this.answerOptions.add(answerOption);
        return this;
    }

    public QuestionModelBuilder withAnswerOptions(List<AnswerOptionModel> answerOptions) {
        this.answerOptions = new ArrayList<>(answerOptions);
        return this;
    }

    public QuestionModelBuilder withCorrectAnswer(CorrectAnswerModel correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public QuestionModel build() {
        QuestionModel question = new QuestionModel();
        question.setText(this.text);
        question.setQuestionType(this.questionType);
        question.setPoints(this.points);
        question.setSurvey(this.survey);
        question.setAnswerOptions(this.answerOptions);
        question.setCorrectAnswer(this.correctAnswer);
        return question;
    }
}
