package com.kleintwins.ftr.league.service;

import com.kleintwins.ftr.core.exception.EntityNotFound;
import com.kleintwins.ftr.core.service.I18nService;
import com.kleintwins.ftr.league.model.*;
import com.kleintwins.ftr.league.code.SurveyType;
import com.kleintwins.ftr.league.repository.ParticipantAnswerRepository;
import com.kleintwins.ftr.league.repository.QuestionRepository;
import com.kleintwins.ftr.league.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepo;
    private final QuestionRepository questionRepo;
    private final ParticipantAnswerRepository participantAnswerRepo;

    private final I18nService i18nService;

    public SurveyModel createSurvey(LeagueModel leagueModel, String name, SurveyType surveyType, LocalDateTime startDate, LocalDateTime endDate, List<QuestionModel> questionModels) {
        SurveyModel surveyModel = new SurveyModel();
        surveyModel.setLeague(leagueModel);
        surveyModel.setName(name);
        surveyModel.setType(surveyType);
        surveyModel.setStartDate(startDate);
        surveyModel.setEndDate(endDate);
        surveyModel.setQuestions(questionModels);

        return surveyRepo.save(surveyModel);
    }

    public List<QuestionModel> addQuestionsToSurvey(String surveyId, List<QuestionModel> questionModels) {
        SurveyModel surveyModel = getSurveyById(surveyId);
        surveyModel.setQuestions(questionModels);
       return surveyRepo.save(surveyModel).getQuestions();
    }

    public void deleteQuestionById(String questionSequence, String surveyId) {
        QuestionId questionId = new QuestionId();
        questionId.setSequence(questionSequence);
        questionId.setSurveyId(surveyId);
        deleteQuestionById(questionId);
    }
    public void deleteQuestionById(QuestionId questionId) {
        questionRepo.deleteById(questionId);
    }

    // Creates or Updates participant's answers to questions
    public List<ParticipantAnswerModel> submitParticipantAnswersForSurvey(
            ParticipantModel participantModel,
            Map<QuestionId, List<AnswerOptionModel>> questionsWithAnswers) {

        List<ParticipantAnswerModel> participantAnswerModels = new ArrayList<>();

        for (Map.Entry<QuestionId, List<AnswerOptionModel>> entry : questionsWithAnswers.entrySet()) {
            QuestionId questionId = entry.getKey();
            List<AnswerOptionModel> providedAnswers = entry.getValue();

            QuestionModel questionModel = getQuestionById(questionId);

            // Check if an existing answer exists for this question and participant
            Optional<ParticipantAnswerModel> existingAnswerOpt = participantAnswerRepo
                    .findByParticipantAndQuestion(participantModel, questionModel);

            ParticipantAnswerModel participantAnswerModel;

            // If an existing answer is found, update it; otherwise, create a new one
            if (existingAnswerOpt.isPresent()) {
                participantAnswerModel = existingAnswerOpt.get();
                participantAnswerModel.setProvidedAnswers(providedAnswers);
            } else {
                participantAnswerModel = createParticipantAnswerModel(
                        questionId, providedAnswers, participantModel, questionModel
                );
            }

            // Add the answer model (either updated or new) to the list
            participantAnswerModels.add(participantAnswerModel);
        }

        // Save all participant answers (either new or updated)
        return participantAnswerRepo.saveAll(participantAnswerModels);
    }

    public void updateCorrectAnswerForQuestion(QuestionId questionId, List<AnswerOptionModel> correctAnswer) {
        QuestionModel questionModel = getQuestionById(questionId);
        CorrectAnswerModel correctAnswerModel = createCorrectAnswerModel(questionModel, correctAnswer);
        questionModel.setCorrectAnswer(correctAnswerModel);
        questionRepo.save(questionModel);
    }

    public void removeCorrectAnswerForQuestion(QuestionId questionId) {
        QuestionModel questionModel = getQuestionById(questionId);
        removeCorrectAnswerForQuestion(questionModel);
    }

    public void removeCorrectAnswerForQuestion(QuestionModel questionModel) {
        questionModel.removeCorrectAnswer();
        questionRepo.save(questionModel);
    }

    public ParticipantAnswerModel getParticipantAnswerById(String surveyId, String questionSequence, String userId, String leagueId) {
        ParticipantAnswerId participantAnswerId = new ParticipantAnswerId(
                surveyId,
                questionSequence,
                userId,
                leagueId
        );
        return getParticipantAnswerById(participantAnswerId);
    }

    public SurveyModel getSurveyById(String surveyId) {
        return surveyRepo.findById(surveyId).orElseThrow(
                () -> {
                    String message = i18nService.translate(
                            "api.survey.findSurveyById.response.error.notFound.message",
                            surveyId
                    );
                    return new EntityNotFound(message);
                });
    }

    public ParticipantAnswerModel getParticipantAnswerById(ParticipantAnswerId participantAnswerId) {
        return participantAnswerRepo.findById(participantAnswerId).orElseThrow(
                () -> {
                    String message = i18nService.translate(
                            "api.survey.findParticipantAnswerById.response.error.notFound.message",
                            participantAnswerId.getUserId(), participantAnswerId.getLeagueId(), participantAnswerId.getQuestionSequence(), participantAnswerId.getSurveyId()
                    );
                    return new EntityNotFound(message);
                });
    }

    public QuestionModel getQuestionById(String sequence, String surveyId) {
        QuestionId questionId = new QuestionId();
        questionId.setSequence(sequence);
        questionId.setSurveyId(surveyId);
        return getQuestionById(questionId);
    }

    public QuestionModel getQuestionById(QuestionId questionId) {
        return questionRepo.findById(questionId).orElseThrow(
                () -> {
                    String message = i18nService.translate(
                            "api.survey.findQuestionById.response.error.notFound.message",
                            questionId.getSequence(), questionId.getSurveyId()
                    );
                    return new EntityNotFound(message);
                });
    }


    private ParticipantAnswerModel createParticipantAnswerModel(
            QuestionId questionId,
            List<AnswerOptionModel> providedAnswers,
            ParticipantModel participantModel,
            QuestionModel questionModel) {

        ParticipantAnswerModel participantAnswerModel = new ParticipantAnswerModel();
        participantAnswerModel.setParticipantAnswerId(new ParticipantAnswerId(
                questionId.getSurveyId(),
                questionId.getSequence(),
                participantModel.getUserId(),
                participantModel.getLeagueId()
        ));
        participantAnswerModel.setProvidedAnswers(providedAnswers);
        participantAnswerModel.setParticipant(participantModel);
        participantAnswerModel.setQuestion(questionModel);

        return participantAnswerModel;
    }

    private CorrectAnswerModel createCorrectAnswerModel(QuestionModel questionModel, List<AnswerOptionModel> correctAnswer) {

        CorrectAnswerModel correctAnswerModel = new CorrectAnswerModel();
        correctAnswerModel.setCorrectAnswerId(new CorrectAnswerId(
                questionModel.getQuestionId().getSurveyId(),
                questionModel.getQuestionId().getSequence()
        ));
        correctAnswerModel.setCorrectAnswers(correctAnswer);
        correctAnswerModel.setQuestion(questionModel);
        return correctAnswerModel;
    }

}
