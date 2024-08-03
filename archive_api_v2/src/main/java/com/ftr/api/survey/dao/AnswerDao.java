package com.ftr.api.survey.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.survey.model.AnswerModel;
import com.ftr.api.survey.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerDao implements IDaoImpl<AnswerModel> {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public AnswerModel saveEntity(AnswerModel entity) {
        return answerRepository.save(entity);
    }

    @Override
    public Optional<AnswerModel> findEntityById(Integer id) {
        return answerRepository.findById(id);
    }

    @Override
    public void updateEntity(AnswerModel entity) {
        answerRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        answerRepository.deleteById(id);
    }

    public Optional<AnswerModel> getUserAnswerForQuestion(Integer userId, Integer questionId) {
        return answerRepository.findByUserModelUserIdAndQuestionModelQuestionId(userId, questionId);
    }
}
