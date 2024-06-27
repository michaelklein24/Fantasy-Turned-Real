package com.ftr.api.score.repository;

import com.ftr.api.score.model.ScoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreModel, Integer> {

    @Query("SELECT SUM(s.pointsAwarded) FROM ScoreModel s WHERE s.userModel.userId = :userId AND s.leagueModel.leagueId = :leagueId")
    BigDecimal findSumByUserIdAndLeagueId(@Param("userId") Integer userId, @Param("leagueId") Integer leagueId);

    @Query("SELECT COUNT(s) + 1 FROM ScoreModel s WHERE s.leagueModel.leagueId = :leagueId AND s.pointsAwarded > (SELECT pointsAwarded FROM ScoreModel WHERE userModel.userId = :userId AND leagueModel.leagueId = :leagueId)")
    Integer findUserPlacementInLeague(@Param("userId") Integer userId, @Param("leagueId") Integer leagueId);
}
