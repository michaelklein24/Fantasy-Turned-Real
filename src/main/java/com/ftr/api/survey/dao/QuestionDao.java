package com.ftr.api.survey.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.survey.model.QuestionModel;
import com.ftr.api.survey.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionDao implements IDaoImpl<QuestionModel> {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public QuestionModel saveEntity(QuestionModel entity) {
        return questionRepository.save(entity);
    }

    @Override
    public Optional<QuestionModel> findEntityById(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public void updateEntity(QuestionModel entity) {
        questionRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        questionRepository.deleteById(id);
    }

    public BigDecimal findSumOfPointsOfAllQuestionsInSurvey(Integer surveyId) {
        return questionRepository.findSumOfPointsForAllQuestionsInSurvey(surveyId);
    }

    public List<QuestionModel> getQuestionsInSurvey(Integer surveyId) {
        return questionRepository.findBySurveyModelSurveyId(surveyId);
    }

}
