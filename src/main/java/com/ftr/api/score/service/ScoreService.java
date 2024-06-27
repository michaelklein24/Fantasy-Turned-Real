package com.ftr.api.score.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ScoreService extends AbstractService {

    private ScoreRepository scoreRepository;

    public BigDecimal getTotalScoreForUserInLeague(Integer userId, Integer leagueId) {
        return scoreRepository.findSumByUserIdAndLeagueId(userId, leagueId);
    }

    public Integer getCurrentPlacementForUserInLeague(Integer userId, Integer leagueId) {
        return scoreRepository.findUserPlacementInLeague(userId, leagueId);
    }
}
