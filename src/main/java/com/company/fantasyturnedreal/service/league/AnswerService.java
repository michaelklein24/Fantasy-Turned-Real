package com.company.fantasyturnedreal.service.league;

import com.company.fantasyturnedreal.dto.league.CreateAnswerRequest;
import com.company.fantasyturnedreal.dto.league.UpdateAnswerRequest;
import com.company.fantasyturnedreal.enums.QuestionStatus;
import com.company.fantasyturnedreal.exception.CannotUpdateEntityException;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.league.Answer;
import com.company.fantasyturnedreal.model.league.League;
import com.company.fantasyturnedreal.model.league.Question;
import com.company.fantasyturnedreal.repository.league.AnswerRepository;
import com.company.fantasyturnedreal.service.AbstractService;
import com.company.fantasyturnedreal.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class AnswerService extends AbstractService {

    @Autowired
    AnswerRepository answerRepo;

    @Autowired
    UserService userService;

    @Autowired
    ScoreService scoreService;

    public List<Answer> getAnswers(Long questionId, Long userId, Long leagueId, Long episodeId) {
        Specification<Answer> spec = Specification.where(null);

        if (questionId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("question").get("questionId"), questionId));
        } else {
            throw new NullPointerException("questionId must not be null.");
        }
        if (leagueId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("question").get("league").get("leagueId"), leagueId));
        }
        if (episodeId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("question").get("league").get("episode").get("episodeId"), episodeId));
        }
        if (userId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("user").get("userId"), userId));
        }

        return answerRepo.findAll(spec);
    }

    public Answer getAnswerById(Long answerId) {
        Answer foundAnswer = answerRepo.findById(answerId).orElse(null);
        if (foundAnswer == null) {
            throw new DataNotFoundException(String.format("Answer with id '%s' does not exist.", answerId));
        }
        return foundAnswer;
    }

    @Autowired
    QuestionService questionService;

    public Answer createAnswer(CreateAnswerRequest createAnswerRequest) {
        Question foundQuestion = questionService.getQuestionById(createAnswerRequest.getQuestionId());
        if (!foundQuestion.getStatus().equals(QuestionStatus.OPEN)) {
            throw new CannotUpdateEntityException(String.format("Cannot create answer because the questions status is not OPEN.  Current Question Status: %s", foundQuestion.getStatus()));
        }
        Answer answer = new Answer();
        answer.setAnswer(createAnswerRequest.getAnswer());
        answer.setUser(userService.getUserById(createAnswerRequest.getUserId()));
        answer.setQuestion(foundQuestion);
        return answerRepo.save(answer);
    }

    @Transactional
    public void updateQuestionWithCorrectAnswer(Long questionId, String correctAnswer) {
        Question foundQuestion = questionService.getQuestionById(questionId);
        foundQuestion.setCorrectAnswer(correctAnswer);

        Set<Answer> answers = foundQuestion.getAnswers();
        answers.stream().forEach(answer -> {
            setAnswerCorrectness(answer.getAnswerId(), correctAnswer);
        });

        questionService.resolveQuestion(foundQuestion);
    }

    public void updateAnswer(UpdateAnswerRequest updateAnswerRequest) {
        Answer foundAnswer = getAnswerById(updateAnswerRequest.getAnswerId());
        if (!foundAnswer.getQuestion().getStatus().equals(QuestionStatus.OPEN)) {
            throw new CannotUpdateEntityException(String.format("Cannot update answer with id '%d' because the questions status is not OPEN.  Current Question Status: %s", foundAnswer.getAnswerId(), foundAnswer.getQuestion().getStatus()));
        }
        foundAnswer.setAnswer(updateAnswerRequest.getAnswer());
        foundAnswer.setTimeUpdated(LocalDateTime.now());
        answerRepo.save(foundAnswer);
    }

    public Answer setAnswerCorrectness(Long answerId, String correctAnswer) {
        Answer foundAnswer = getAnswerById(answerId);
        boolean isCorrect = isAnswerCorrect(foundAnswer, correctAnswer);
        foundAnswer.setIsCorrect(isCorrect);
        if (isCorrect) {
            scoreService.addScore(foundAnswer);
        }
        return foundAnswer;
    }

    public boolean isAnswerCorrect(Answer answer, String correctAnswer) {
        return answer.getAnswer().equals(correctAnswer);
    }

}
