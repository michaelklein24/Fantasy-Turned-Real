package com.ftr.api.survey.service;

import com.ftr.api.survey.model.QuestionModel;
import com.ftr.api.survey.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionModel> getQuestionsForSurveyForUser(Integer userId, Integer surveyId) {
        return questionRepository.findBySurveyModelSurveyId(surveyId);
    }

    public BigDecimal getTotalPotentialPointsOfAllQuestionsInSurvey(Integer surveyId) {
        return questionRepository.findSumOfPointsForAllQuestionsInSurvey(surveyId);
    }

    public Optional<QuestionModel> getQuestionByQuestionId(Integer questionId) {
        return questionRepository.findById(questionId);
    }
}
