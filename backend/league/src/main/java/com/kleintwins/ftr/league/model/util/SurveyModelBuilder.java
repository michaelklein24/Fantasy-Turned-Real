package com.kleintwins.ftr.league.model.util;

import com.kleintwins.ftr.league.model.LeagueModel;
import com.kleintwins.ftr.league.model.QuestionModel;
import com.kleintwins.ftr.league.model.SurveyModel;

import java.util.ArrayList;
import java.util.List;

public class SurveyModelBuilder {
    private String name;
    private LeagueModel league;
    private List<QuestionModel> questions = new ArrayList<>();

    public SurveyModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SurveyModelBuilder withLeague(LeagueModel league) {
        this.league = league;
        return this;
    }

    public SurveyModelBuilder addQuestion(QuestionModel question) {
        this.questions.add(question);
        return this;
    }

    public SurveyModelBuilder withQuestions(List<QuestionModel> questions) {
        this.questions = new ArrayList<>(questions);
        return this;
    }

    // Build method to construct SurveyModel
    public SurveyModel build() {
        SurveyModel survey = new SurveyModel();
        survey.setName(this.name);
        survey.setLeague(this.league);
        survey.setQuestions(this.questions);
        return survey;
    }

}
