package com.kleintwins.ftr.league.util;

import com.kleintwins.ftr.league.dto.CreateSurveyResponse;
import com.kleintwins.ftr.league.dto.GetSurveyByIdResponse;
import com.kleintwins.ftr.league.dto.GetSurveysResponse;
import com.kleintwins.ftr.league.dto.Survey;
import com.kleintwins.ftr.league.model.SurveyModel;

import java.util.Collections;
import java.util.List;

public class SurveyDtoBuilder {

    public static GetSurveysResponse buildGetSurveysResponse(List<SurveyModel> surveyModels) {
        List<Survey> surveys = surveyModels.stream().map(SurveyDtoBuilder::buildSurvey).toList();
        return new GetSurveysResponse(surveys);
    }

    public static GetSurveyByIdResponse buildGetSurveyByIdResponse(SurveyModel surveyModel) {
        Survey survey = SurveyDtoBuilder.buildSurvey(surveyModel);
        return new GetSurveyByIdResponse(survey);
    }

    private static Survey buildSurvey(SurveyModel surveyModel) {
        Survey survey = new Survey();
        survey.setId(surveyModel.getSurveyId());
        survey.setName(surveyModel.getName());
        survey.setType(surveyModel.getType());
        survey.setStatus(surveyModel.getCurrentStatus().getSurveyStatusId().getSurveyStatus());
        survey.setStartTime(surveyModel.getStartTime());
        survey.setEndTime(surveyModel.getEndTime());
        survey.setCreateTime(survey.getCreateTime());
        survey.setUpdateTime(survey.getUpdateTime());
        survey.setQuestions(Collections.emptyList());
        return survey;
    }

    public static CreateSurveyResponse buildCreateSurveyResponse(SurveyModel surveyModel) {
        Survey survey = buildSurvey(surveyModel);
        return new CreateSurveyResponse(survey);
    }
}
