package com.ftr.api.score.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.score.model.ScoreModel;
import com.ftr.api.score.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ScoreDao implements IDaoImpl<ScoreModel> {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public ScoreModel saveEntity(ScoreModel entity) {
        return scoreRepository.save(entity);
    }

    @Override
    public Optional<ScoreModel> findEntityById(Integer id) {
        return scoreRepository.findById(id);
    }

    @Override
    public void updateEntity(ScoreModel entity) {
        scoreRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        scoreRepository.deleteById(id);
    }

    public BigDecimal getTotalScoreForUserInLeague(Integer userId, Integer leagueId) {
        return scoreRepository.findSumByUserIdAndLeagueId(userId, leagueId);
    }
    public Integer getUserPlacementInLeague(Integer userId, Integer leagueId) {
        return scoreRepository.findUserPlacementInLeague(userId, leagueId);
    }

    public BigDecimal getScoreEarnedByUserInSurvey(Integer userId, Integer surveyId) {
        return scoreRepository.findSumByUserIdAndSurveyId(userId, surveyId);
    }

    public Integer getUserRankInSurvey(Integer userId, Integer surveyId) {
        return scoreRepository.findUserRankInSurvey(userId, surveyId);
    }
}
