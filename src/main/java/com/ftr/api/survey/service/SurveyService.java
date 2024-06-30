package com.ftr.api.survey.service;

import com.ftr.api.league.dao.ParticipantDao;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.dao.LeagueDao;
import com.ftr.api.score.dao.ScoreDao;
import com.ftr.api.show.dao.EpisodeDao;
import com.ftr.api.show.model.EpisodeModel;
import com.ftr.api.survey.code.SurveyStatusCode;
import com.ftr.api.survey.dao.AnswerDao;
import com.ftr.api.survey.dao.QuestionDao;
import com.ftr.api.survey.dao.SurveyDao;
import com.ftr.api.survey.dto.*;
import com.ftr.api.survey.model.AnswerModel;
import com.ftr.api.survey.model.QuestionModel;
import com.ftr.api.survey.model.SurveyModel;
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

    private final SurveyDao surveyDao;
    private final ScoreDao scoreDao;
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    private final ParticipantDao participantDao;
    private final EpisodeDao episodeDao;
    private final LeagueDao leagueDao;

    public GetSurveyDetailsByIdResponse getSurveyDetailsBySurveyIdForUser(Integer surveyId, Integer userId) {
        SurveyModel surveyModel = surveyDao.findEntityById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find survey with surveyId '%d'", surveyId)));

        GetSurveyDetailsByIdResponse response = new GetSurveyDetailsByIdResponse();

        List<QuestionModel> questionModels = questionDao.getQuestionsInSurvey(surveyModel.getSurveyId());

        List<QuestionDetails> questionDetails = new ArrayList<>();
        for (QuestionModel questionModel: questionModels) {
            AnswerModel answerModel = answerDao.getUserAnswerForQuestion(userId, questionModel.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find answer for userId '%d' and questionId '%d'", userId, questionModel.getQuestionId())));
            QuestionDetails questionDetail = createQuestionDetail(questionModel, answerModel);
            questionDetails.add(questionDetail);
        }

        SurveyDetails surveyDetails = createSurveyDetails(surveyModel);
        surveyDetails.setQuestionDetails(questionDetails);
        response.setSurveyDetails(surveyDetails);

        BigDecimal pointsEarnedFromSurvey = scoreDao.getScoreEarnedByUserInSurvey(surveyId, userId);
        BigDecimal totalPotentialPointsInSurvey = questionDao.findSumOfPointsOfAllQuestionsInSurvey(surveyId);
        Integer placement = scoreDao.getUserRankInSurvey(userId, surveyId);
        Integer totalNumberOfPlayers = participantDao.getTotalNumberOfParticipantsInLeague(surveyModel.getLeagueModel().getLeagueId());

        ScoringDetails scoringDetails = createScoringDetails(pointsEarnedFromSurvey, totalPotentialPointsInSurvey, placement, totalNumberOfPlayers);
        response.setScoringDetails(scoringDetails);

        return response;
    }

    @Transactional
    public CreateSurveyResponse createSurvey(CreateSurveyRequest createSurveyRequest, Integer userId) {
        Integer episodeId = createSurveyRequest.getEpisodeId();
        Integer leagueId = createSurveyRequest.getLeagueId();
        boolean doesSurveyAlreadyExist = surveyDao.doesSurveyForLeagueExist(episodeId, leagueId);

        if (doesSurveyAlreadyExist) {
            throw new EntityExistsException(String.format("Survey has already been created for episodeId '%d' in leagueId '%d'", episodeId, leagueId));
        }

        SurveyModel surveyModel = new SurveyModel();
        surveyModel.setStatus(SurveyStatusCode.CREATED);

        EpisodeModel episodeModel = episodeDao.findEntityById(episodeId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find episode with episodeId '%d'", episodeId)));
        surveyModel.setEpisodeModel(episodeModel);

        LeagueModel leagueModel = leagueDao.findEntityById(leagueId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find league with leagueId '%d'", leagueId)));
        surveyModel.setLeagueModel(leagueModel);

        surveyModel = surveyDao.saveEntity(surveyModel);

        List<QuestionModel> questionModels = new ArrayList<>();
        for (QuestionDetails questionDetail : createSurveyRequest.getSurveyDetails().getQuestionDetails()) {
            QuestionModel questionModel = buildQuestionModel(questionDetail, surveyModel);
            questionModel = questionDao.saveEntity(questionModel);
            questionModels.add(questionModel);
        }

        SurveyDetails surveyDetails = createSurveyDetails(surveyModel, questionModels);

        CreateSurveyResponse response = new CreateSurveyResponse();
        response.setSurveyDetails(surveyDetails);

        return response;
    }

    private SurveyDetails createSurveyDetails(SurveyModel surveyModel, List<QuestionModel> questionModels) {
        SurveyDetails surveyDetails = createSurveyDetails(surveyModel);
        List<QuestionDetails> questionDetails = new ArrayList<>();
        for (QuestionModel questionModel : questionModels) {
            QuestionDetails questionDetail = createQuestionDetail(questionModel);
            questionDetails.add(questionDetail);
        }
        surveyDetails.setQuestionDetails(questionDetails);
        return surveyDetails;
    }

    private SurveyDetails createSurveyDetails(SurveyModel surveyModel) {
        SurveyDetails surveyDetails = new SurveyDetails();
        surveyDetails.setSurveyId(surveyModel.getSurveyId());
        surveyDetails.setEpisodeTitle(surveyModel.getEpisodeModel().getTitle());
        surveyDetails.setEpisodeNumber(surveyModel.getEpisodeModel().getEpisodeNumber());
        return surveyDetails;
    }

    private QuestionDetails createQuestionDetail(QuestionModel questionModel, AnswerModel answerModel) {
        AnswerDetails answerDetails = createAnswerDetails(answerModel, questionModel);
        QuestionDetails questionDetail = new QuestionDetails();
        questionDetail.setAnswerDetails(answerDetails);
        questionDetail.setQuestionId(questionModel.getQuestionId());
        questionDetail.setQuestion(questionModel.getQuestion());
        questionDetail.setQuestionTypeCode(questionModel.getQuestionType());
        questionDetail.setCorrectAnswer(questionModel.getCorrectAnswer());
        questionDetail.setPossiblePoints(questionModel.getPoints());

        return questionDetail;
    }

    private QuestionDetails createQuestionDetail(QuestionModel questionModel) {
        QuestionDetails questionDetail = new QuestionDetails();
        questionDetail.setQuestionId(questionModel.getQuestionId());
        questionDetail.setPossiblePoints(questionModel.getPoints());
        questionDetail.setQuestionTypeCode(questionModel.getQuestionType());
        questionDetail.setQuestionNumber(questionModel.getQuestionNumber());
        questionDetail.setQuestion(questionModel.getQuestion());

        return questionDetail;
    }

    private AnswerDetails createAnswerDetails(AnswerModel answerModel, QuestionModel questionModel) {
        AnswerDetails answerDetails = new AnswerDetails();
        answerDetails.setAnswerId(answerModel.getAnswerId());
        answerDetails.setAnswer(answerModel.getAnswer());
        answerDetails.setCorrect(answerModel.isCorrect());
        answerDetails.setAwardedPoints(answerModel.isCorrect() ? questionModel.getPoints() : BigDecimal.ZERO);

        return answerDetails;
    }

    private ScoringDetails createScoringDetails(BigDecimal pointsEarnedFromSurvey, BigDecimal totalPotentialPointsInSurvey, Integer placement, Integer totalNumberOfPlayers) {
        ScoringDetails scoringDetails = new ScoringDetails();

        scoringDetails.setPointsAwarded(pointsEarnedFromSurvey);

        scoringDetails.setPlacement(placement);

        scoringDetails.setTotalPossiblePoints(totalPotentialPointsInSurvey);

        if (totalPotentialPointsInSurvey.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal percentageObtained = pointsEarnedFromSurvey
                    .divide(totalPotentialPointsInSurvey, 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100));
            scoringDetails.setPercentageObtained(percentageObtained);
        } else {
            scoringDetails.setPercentageObtained(BigDecimal.ZERO);
        }

        scoringDetails.setTotalNumberOfPlayers(totalNumberOfPlayers);

        return scoringDetails;
    }
    private QuestionModel buildQuestionModel(QuestionDetails questionDetails, SurveyModel surveyModel) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setQuestion(questionDetails.getQuestion());
        questionModel.setQuestionType(questionDetails.getQuestionTypeCode());
        questionModel.setPoints(questionDetails.getPossiblePoints());
        questionModel.setQuestionNumber(questionDetails.getQuestionNumber());
        questionModel.setSurveyModel(surveyModel);
        return questionModel;
    }
}
