package com.ftr.api.survey.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.survey.dto.AnswerDetails;
import com.ftr.api.survey.model.AnswerModel;
import com.ftr.api.survey.model.QuestionModel;
import com.ftr.api.survey.repository.AnswerRepository;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.EntityNotFoundException;
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

    public AnswerModel saveAnswer(AnswerDetails answerDetails, QuestionModel questionModel, UserModel userModel) {
        AnswerModel answerModel = new AnswerModel();
        Integer answerId = answerModel.getAnswerId();
        if (answerId != null) {
            answerModel = answerRepository.findById(answerId).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find answer with answerId '%d'", answerId)));
        }
        answerModel.setAnswer(answerDetails.getAnswer());
        answerModel.setQuestionModel(questionModel);
        answerModel.setUserModel(userModel);

        return answerModel;
    }
}
