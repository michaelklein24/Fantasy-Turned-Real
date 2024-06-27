package com.ftr.api.survey.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.survey.model.AnswerModel;
import com.ftr.api.survey.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService extends AbstractService {

    private final AnswerRepository answerRepository;

    public Optional<AnswerModel> getUsersAnswerForQuestion(Integer userId, Integer questionId) {
        return answerRepository.findByUserModelUserIdAndQuestionModelQuestionId(userId, questionId);
    }
}
