package com.kleintwins.ftr.league.model.util;

import com.kleintwins.ftr.league.model.AnswerOptionModel;
import com.kleintwins.ftr.league.model.QuestionModel;

public class AnswerOptionModelBuilder {

    private String text;
    private QuestionModel question;

    public AnswerOptionModelBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public AnswerOptionModelBuilder withQuestion(QuestionModel question) {
        this.question = question;
        return this;
    }

    public AnswerOptionModel build() {
        AnswerOptionModel answerOption = new AnswerOptionModel();
        answerOption.setText(this.text);
        answerOption.setQuestion(this.question);
        return answerOption;
    }
}

