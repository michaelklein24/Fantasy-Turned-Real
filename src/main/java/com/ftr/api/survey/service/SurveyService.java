package com.ftr.api.survey.service;

import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.service.LeagueDao;
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
    private final LeagueDao leagueDao;

    public GetSurveyDetailsByIdResponse getSurveyDetailsBySurveyIdForUser(Integer surveyId, Integer userId) {
        SurveyModel surveyModel = getSurveyById(surveyId);

        GetSurveyDetailsByIdResponse response = new GetSurveyDetailsByIdResponse();
        SurveyDetails surveyDetails = createSurveyDetails(surveyModel, userId);
        response.setSurveyDetails(surveyDetails);

        ScoringDetails scoringDetails = createScoringDetails(surveyModel, surveyId, userId);
        response.setScoringDetails(scoringDetails);

        return response;
    }

    @Transactional
    public CreateSurveyResponse createSurvey(CreateSurveyRequest createSurveyRequest, Integer userId) {
        validateSurveyUniqueness(createSurveyRequest);

        SurveyModel surveyModel = new SurveyModel();
        surveyModel.setStatus(SurveyStatusCode.CREATED);

        EpisodeModel episodeModel = getEpisodeById(createSurveyRequest.getEpisodeId());
        surveyModel.setEpisodeModel(episodeModel);

        LeagueModel leagueModel = getLeagueById(createSurveyRequest.getLeagueId());
        surveyModel.setLeagueModel(leagueModel);

        surveyModel = surveyRepository.save(surveyModel);

        List<QuestionModel> questionModels = new ArrayList<>();
        for (QuestionDetails questionDetail : createSurveyRequest.getSurveyDetails().getQuestionDetails()) {
            QuestionModel questionModel = questionService.createQuestion(questionDetail, surveyModel);
            questionModels.add(questionModel);
        }

        SurveyDetails surveyDetails = createSurveyDetails(surveyModel, questionModels);

        CreateSurveyResponse response = new CreateSurveyResponse();
        response.setSurveyDetails(surveyDetails);

        return response;
    }

    private SurveyModel getSurveyById(Integer surveyId) {
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find survey with surveyId '%d'", surveyId)));
    }

    private void validateSurveyUniqueness(CreateSurveyRequest createSurveyRequest) {
        Integer episodeId = createSurveyRequest.getEpisodeId();
        Integer leagueId = createSurveyRequest.getLeagueId();
        boolean doesSurveyAlreadyExist = surveyRepository.existsByEpisodeModelEpisodeIdAndLeagueModelLeagueId(episodeId, leagueId);

        if (doesSurveyAlreadyExist) {
            throw new EntityExistsException(String.format("Survey has already been created for episodeId '%d' in leagueId '%d'", episodeId, leagueId));
        }
    }

    private EpisodeModel getEpisodeById(Integer episodeId) {
        return episodeService.getEpisodeByEpisodeId(episodeId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find episode with episodeId '%d'", episodeId)));
    }

    private LeagueModel getLeagueById(Integer leagueId) {
        return leagueDao.getLeagueByLeagueId(leagueId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find league with leagueId '%d'", leagueId)));
    }

    private SurveyDetails createSurveyDetails(SurveyModel surveyModel, Integer userId) {
        SurveyDetails surveyDetails = new SurveyDetails();
        surveyDetails.setSurveyId(surveyModel.getSurveyId());
        surveyDetails.setEpisodeTitle(surveyModel.getEpisodeModel().getTitle());
        surveyDetails.setEpisodeNumber(surveyModel.getEpisodeModel().getEpisodeNumber());

        List<QuestionDetails> questionDetails = new ArrayList<>();
        List<QuestionModel> questionModels = questionService.getQuestionsForSurveyForUser(surveyModel.getSurveyId(), userId);

        for (QuestionModel questionModel : questionModels) {
            try {
                AnswerModel answerModel = answerService.getUsersAnswerForQuestion(userId, questionModel.getQuestionId())
                        .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find answer for user Id '%d' and questionId '%d'", userId, questionModel.getQuestionId())));
                QuestionDetails questionDetail = createQuestionDetail(questionModel, answerModel);
                questionDetails.add(questionDetail);
            } catch (EntityNotFoundException e) {
                // Handle missing answers if necessary
            }
        }

        surveyDetails.setQuestionDetails(questionDetails);
        return surveyDetails;
    }

    public SubmitSurveyResponse submitSurvey(SubmitSurveyRequest submitSurveyRequest) {
        List<AnswerDetails> submittedAnswers = submitSurveyRequest.getAnswers();
        List<AnswerModel> answerModels = new ArrayList<>();
        for (AnswerDetails submittedAnswer : submittedAnswers) {

            QuestionModel questionModel = questionService.getQuestionByQuestionId(questionId);
        }
    }

    private SurveyDetails createSurveyDetails(SurveyModel surveyModel, List<QuestionModel> questionModels) {
        SurveyDetails surveyDetails = new SurveyDetails();
        surveyDetails.setEpisodeTitle(surveyModel.getEpisodeModel().getTitle());
        surveyDetails.setEpisodeNumber(surveyModel.getEpisodeModel().getEpisodeNumber());

        List<QuestionDetails> questionDetails = questionModels.stream().map(this::createQuestionDetail).toList();
        surveyDetails.setQuestionDetails(questionDetails);

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

    private ScoringDetails createScoringDetails(SurveyModel surveyModel, Integer surveyId, Integer userId) {
        ScoringDetails scoringDetails = new ScoringDetails();

        BigDecimal pointsEarnedFromSurvey = scoreService.getTotalPointsEarnedFromSurveyForUser(surveyId, userId);
        scoringDetails.setPointsAwarded(pointsEarnedFromSurvey);

        Integer placement = scoreService.getUserRankInSurvey(userId, surveyId);
        scoringDetails.setPlacement(placement);

        BigDecimal totalPotentialPointsInSurvey = questionService.getTotalPotentialPointsOfAllQuestionsInSurvey(surveyId);
        scoringDetails.setTotalPossiblePoints(totalPotentialPointsInSurvey);

        if (totalPotentialPointsInSurvey.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal percentageObtained = pointsEarnedFromSurvey
                    .divide(totalPotentialPointsInSurvey, 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100));
            scoringDetails.setPercentageObtained(percentageObtained);
        } else {
            scoringDetails.setPercentageObtained(BigDecimal.ZERO);
        }

        Integer totalNumberOfPlayers = participantService.getTotalNumberOfParticipantsInLeague(surveyModel.getLeagueModel().getLeagueId());
        scoringDetails.setTotalNumberOfPlayers(totalNumberOfPlayers);

        return scoringDetails;
    }
}
