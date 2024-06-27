package com.ftr.api.survey.service;

import com.ftr.api.survey.dto.QuestionDetails;
import com.ftr.api.survey.model.AnswerModel;
import com.ftr.api.survey.model.QuestionModel;
import com.ftr.api.survey.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionModel> getQuestionsForSurveyForUser(Integer userId, Integer surveyId) {
        return questionRepository.findBySurveyModelSurveyId(surveyId);
    }

    public BigDecimal getTotalPotentialPointsOfAllQuestionsInSurvey(Integer surveyId) {
        return questionRepository.findSumOfPotentialPointsForQuestionsInSurvey(surveyId);
    }

    public QuestionModel createQuestion(QuestionDetails questionDetails) {

    }
}
