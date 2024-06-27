package com.ftr.api.survey.service;

import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.service.LeagueService;
import com.ftr.api.league.service.ParticipantService;
import com.ftr.api.score.service.ScoreService;
import com.ftr.api.show.model.EpisodeModel;
import com.ftr.api.show.service.EpisodeService;
import com.ftr.api.survey.code.SurveyStatusCode;
import com.ftr.api.survey.dto.*;
import com.ftr.api.survey.model.AnswerModel;
import com.ftr.api.survey.model.QuestionModel;
import com.ftr.api.survey.model.SurveyModel;
import com.ftr.api.survey.repository.SurveyRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final ScoreService scoreService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ParticipantService participantService;
    private final EpisodeService episodeService;
    private final LeagueService leagueService;

    public GetSurveyDetailsByIdResponse getSurveyDetailsBySurveyIdForUser(Integer surveyId, Integer userId) {

        SurveyModel surveyModel = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find survey with surveyId '%d'", surveyId)));

        GetSurveyDetailsByIdResponse getSurveyDetailsByIdResponse = new GetSurveyDetailsByIdResponse();

        SurveyDetails surveyDetails = new SurveyDetails();
        surveyDetails.setSurveyId(surveyModel.getSurveyId());
        surveyDetails.setEpisodeTitle(surveyModel.getEpisodeModel().getTitle());
        surveyDetails.setEpisodeNumber(surveyModel.getEpisodeModel().getEpisodeNumber());

        List<QuestionDetails> questionDetails = new ArrayList<>();

        List<QuestionModel> questionModels = questionService.getQuestionsForSurveyForUser(surveyId, userId);
        for (QuestionModel questionModel : questionModels) {
            AnswerModel answerModel = answerService.getUsersAnswerForQuestion(userId, questionModel.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find answer for user Id '%d' and questionId '%d'", userId, questionModel.getQuestionId())));
            QuestionDetails questionDetail = createQuestionDetail(questionModel, answerModel);
            questionDetails.add(questionDetail);
        }

        surveyDetails.setQuestionDetails(questionDetails);
        getSurveyDetailsByIdResponse.setSurveyDetails(surveyDetails);

        ScoringDetails scoringDetails = new ScoringDetails();

        BigDecimal pointsEarnedFromSurvey = scoreService.getTotalPointsEarnedFromSurveyForUser(surveyId, userId);
        scoringDetails.setPointsAwarded(pointsEarnedFromSurvey);

        Integer placement = scoreService.getUserRankInSurvey(userId, surveyId);
        scoringDetails.setPlacement(placement);

        BigDecimal totalPotentialPointsInSurvey = questionService.getTotalPotentialPointsOfAllQuestionsInSurvey(surveyId);
        scoringDetails.setTotalPossiblePoints(totalPotentialPointsInSurvey);
        scoringDetails.setPercentageObtained(pointsEarnedFromSurvey.divide(totalPotentialPointsInSurvey, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));

        Integer totalNumberOfPlayers = participantService.getTotalNumberOfParticipantsInLeague(surveyModel.getLeagueModel().getLeagueId());
        scoringDetails.setTotalNumberOfPlayers(totalNumberOfPlayers);

        getSurveyDetailsByIdResponse.setScoringDetails(scoringDetails);
        return getSurveyDetailsByIdResponse;
    }

    private static QuestionDetails createQuestionDetail(QuestionModel questionModel, AnswerModel answerModel) {
        AnswerDetails answerDetails = new AnswerDetails();
        answerDetails.setAnswerId(answerModel.getAnswerId());
        answerDetails.setAnswer(answerModel.getAnswer());
        answerDetails.setCorrect(answerModel.isCorrect());
        answerDetails.setAwardedPoints(answerModel.isCorrect() ? questionModel.getPoints() : BigDecimal.ZERO);

        QuestionDetails questionDetail = new QuestionDetails();
        questionDetail.setAnswerDetails(answerDetails);
        questionDetail.setQuestionId(questionModel.getQuestionId());
        questionDetail.setQuestion(questionModel.getQuestion());
        questionDetail.setQuestionTypeCode(questionModel.getQuestionType());
        questionDetail.setCorrectAnswer(questionModel.getCorrectAnswer());
        questionDetail.setPossiblePoints(questionModel.getPoints());
        return questionDetail;
    }

    @Transactional
    public CreateSurveyResponse createSurvey(CreateSurveyRequest createSurveyRequest, Integer userId) {
        Integer episodeId = createSurveyRequest.getEpisodeId();
        Integer leagueId = createSurveyRequest.getLeagueId();
        boolean doesSurveyAlreadyExist = surveyRepository.existsByEpisodeModelEpisodeIdAndLeagueModelLeagueId(episodeId, leagueId);

        if (doesSurveyAlreadyExist) {
            throw new EntityExistsException(String.format("Survey has already been created for episodeId '%d' in leagueId '%d'", episodeId, leagueId));
        }

        SurveyModel surveyModel = new SurveyModel();
        surveyModel.setStatus(SurveyStatusCode.CREATED);

        EpisodeModel episodeModel = episodeService.getEpisodeByEpisodeId(episodeId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find episode with episodeId '%d'", episodeId)));
        surveyModel.setEpisodeModel(episodeModel);

        LeagueModel leagueModel = leagueService.getLeagueByLeagueId(episodeId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find league with leagueId '%d'", leagueId)));
        surveyModel.setLeagueModel(leagueModel);

        surveyModel = surveyRepository.save(surveyModel);

        List<QuestionModel> questionModels = new ArrayList<>();
        for (QuestionDetails questionDetail : createSurveyRequest.getQuestions()) {
            QuestionModel questionModel = questionService.createQuestion(questionDetail);
            questionModels.add(questionModel);
        }

        CreateSurveyResponse createSurveyResponse = new CreateSurveyResponse();
        createSurveyResponse.setSurveyDetails();

    }
}
