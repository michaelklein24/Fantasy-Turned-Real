package com.ftr.api.survey.service;

import com.ftr.api.league.dao.LeagueUserRoleDao;
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
import com.ftr.api.user.dao.UserDao;
import com.ftr.api.user.model.UserModel;
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
    private final LeagueUserRoleDao leagueUserRoleDao;
    private final EpisodeDao episodeDao;
    private final LeagueDao leagueDao;
    private final UserDao userDao;

    public GetSurveyDetailsByIdResponse getSurveyDetailsBySurveyIdForUser(Integer surveyId, Integer userId) {
        SurveyModel surveyModel = surveyDao.findEntityById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find survey with surveyId '%d'", surveyId)));

        GetSurveyDetailsByIdResponse response = new GetSurveyDetailsByIdResponse();

        List<QuestionModel> questionModels = questionDao.getQuestionsInSurvey(surveyModel.getSurveyId());

        List<QuestionDto> questionDtos = new ArrayList<>();
        List<AnswerDto> answerDtos = new ArrayList<>();
        for (QuestionModel questionModel: questionModels) {
            AnswerModel answerModel = answerDao.getUserAnswerForQuestion(userId, questionModel.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find answer for userId '%d' and questionId '%d'", userId, questionModel.getQuestionId())));
            AnswerDto answerDto = createAnswerDto(answerModel);
            answerDtos.add(answerDto);

            QuestionDto questionDto = createQuestionDto(questionModel);
            questionDtos.add(questionDto);
        }

        SurveyDto surveyDto = createSurveyDetails(surveyModel);
        surveyDto.setQuestions(questionDtos);
        surveyDto.setAnswers(answerDtos);

        response.setSurveyDto(surveyDto);

        BigDecimal pointsEarnedFromSurvey = scoreDao.getScoreEarnedByUserInSurvey(surveyId, userId);
        BigDecimal totalPotentialPointsInSurvey = questionDao.findSumOfPointsOfAllQuestionsInSurvey(surveyId);
        Integer placement = scoreDao.getUserRankInSurvey(userId, surveyId);
        Integer totalNumberOfPlayers = leagueUserRoleDao.getTotalNumberOfLeagueUserRoleInLeague(surveyModel.getLeagueModel().getLeagueId());

        ScoringSummaryDto scoringSummaryDto = createScoringDto(pointsEarnedFromSurvey, totalPotentialPointsInSurvey, placement, totalNumberOfPlayers);
        response.setScoringSummaryDto(scoringSummaryDto);

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
        for (QuestionDto questionDto : createSurveyRequest.getSurveyDto().getQuestions()) {
            QuestionModel questionModel = buildQuestionModel(questionDto, surveyModel);
            questionModel = questionDao.saveEntity(questionModel);
            questionModels.add(questionModel);
        }

        SurveyDto surveyDto = createSurveyDetails(surveyModel, questionModels);

        CreateSurveyResponse response = new CreateSurveyResponse();
        response.setSurveyDto(surveyDto);

        return response;
    }

    public SubmitSurveyResponse submitSurveyAnswers(SubmitSurveyRequest submitSurveyRequest, Integer userId) {

        List<AnswerModel> answerModels = submitSurveyRequest.getAnswers().stream().map(answer -> {
            AnswerModel answerModel = answerDao.findEntityById(answer.getAnswerId()).orElse(new AnswerModel());
            UserModel userModel = userDao.findEntityById(userId).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with userId '%d'", userId)));
            answerModel.setUserModel(userModel);
            answerModel.setAnswer(answer.getAnswer());
            answerModel.setQuestionModel(questionDao.findEntityById(answer.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find question with questionId '%d'", userId))));
            return answerDao.saveEntity(answerModel);
        }).toList();

        List<AnswerDto> answerDtos = answerModels.stream().map(this::createAnswerDto).toList();

        SubmitSurveyResponse response = new SubmitSurveyResponse();
        response.setAnswers(answerDtos);
        return response;
    }

    private SurveyDto createSurveyDetails(SurveyModel surveyModel, List<QuestionModel> questionModels) {
        SurveyDto surveyDto = createSurveyDetails(surveyModel);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (QuestionModel questionModel : questionModels) {
            QuestionDto questionDto = createQuestionDto(questionModel);
            questionDtos.add(questionDto);
        }
        surveyDto.setQuestions(questionDtos);
        return surveyDto;
    }

    private SurveyDto createSurveyDetails(SurveyModel surveyModel) {
        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setSurveyId(surveyModel.getSurveyId());
        surveyDto.setEpisodeTitle(surveyModel.getEpisodeModel().getTitle());
        surveyDto.setEpisodeNumber(surveyModel.getEpisodeModel().getEpisodeNumber());
        return surveyDto;
    }

    private QuestionDto createQuestionDto(QuestionModel questionModel) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(questionModel.getQuestionId());
        questionDto.setQuestion(questionModel.getQuestion());
        questionDto.setQuestionTypeCode(questionModel.getQuestionType());
        questionDto.setCorrectAnswer(questionModel.getCorrectAnswer());
        questionDto.setPossiblePoints(questionModel.getPoints());
        questionDto.setQuestionNumber(questionModel.getQuestionNumber());

        return questionDto;
    }

    private AnswerDto createAnswerDto(AnswerModel answerModel) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerId(answerModel.getAnswerId());
        answerDto.setAnswer(answerModel.getAnswer());
        answerDto.setCorrect(answerModel.isCorrect());
        answerDto.setAwardedPoints(answerModel.isCorrect() ? answerModel.getQuestionModel().getPoints() : BigDecimal.ZERO);
        answerDto.setQuestionId(answerModel.getQuestionModel().getQuestionId());

        return answerDto;
    }

    private ScoringSummaryDto createScoringDto(BigDecimal pointsEarnedFromSurvey, BigDecimal totalPotentialPointsInSurvey, Integer placement, Integer totalNumberOfPlayers) {
        ScoringSummaryDto scoringSummaryDto = new ScoringSummaryDto();

        scoringSummaryDto.setPointsAwarded(pointsEarnedFromSurvey);

        scoringSummaryDto.setPlacement(placement);

        scoringSummaryDto.setTotalPossiblePoints(totalPotentialPointsInSurvey);

        if (totalPotentialPointsInSurvey.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal percentageObtained = pointsEarnedFromSurvey
                    .divide(totalPotentialPointsInSurvey, 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100));
            scoringSummaryDto.setPercentageObtained(percentageObtained);
        } else {
            scoringSummaryDto.setPercentageObtained(BigDecimal.ZERO);
        }

        scoringSummaryDto.setTotalNumberOfPlayers(totalNumberOfPlayers);

        return scoringSummaryDto;
    }
    private QuestionModel buildQuestionModel(QuestionDto questionDto, SurveyModel surveyModel) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setQuestion(questionDto.getQuestion());
        questionModel.setQuestionType(questionDto.getQuestionTypeCode());
        questionModel.setPoints(questionDto.getPossiblePoints());
        questionModel.setQuestionNumber(questionDto.getQuestionNumber());
        questionModel.setSurveyModel(surveyModel);
        return questionModel;
    }
}
