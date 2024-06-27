package com.ftr.api.score.repository;

import com.ftr.api.score.model.ScoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreModel, Integer> {

    @Query("SELECT SUM(s.pointsAwarded) FROM ScoreModel s " +
            "WHERE s.userModel.userId = :userId AND s.leagueModel.leagueId = :leagueId")
    BigDecimal findSumByUserIdAndLeagueId(@Param("userId") Integer userId, @Param("leagueId") Integer leagueId);

    @Query("SELECT COUNT(s) + 1 FROM ScoreModel s " +
            "WHERE s.leagueModel.leagueId = :leagueId AND s.pointsAwarded > " +
            "(SELECT pointsAwarded FROM ScoreModel " +
            "WHERE userModel.userId = :userId AND leagueModel.leagueId = :leagueId)")
    Integer findUserPlacementInLeague(@Param("userId") Integer userId, @Param("leagueId") Integer leagueId);

    @Query("SELECT SUM(s.pointsAwarded) FROM ScoreModel s " +
            "WHERE s.userModel.userId = :userId AND " +
            "s.questionType = 'QUESTION' AND " +
            "s.questionModel.surveyModel.surveyId = :surveyId")
    BigDecimal findSumByUserIdAndSurveyId(@Param("userId") Integer userId, @Param("surveyId") Integer surveyId);

    @Query("SELECT COUNT(DISTINCT s1.userModel.userId) + 1 " +
            "FROM ScoreModel s1 " +
            "JOIN ScoreModel s2 ON s1.userModel.userId = s2.userModel.userId " +
            "WHERE s2.surveyModel.surveyId = :surveyId " +
            "AND (SELECT SUM(s3.pointsAwarded) FROM ScoreModel s3 WHERE s3.userModel.userId = s1.userModel.userId AND s3.surveyModel.surveyId = :surveyId) > " +
            "(SELECT SUM(s4.pointsAwarded) FROM ScoreModel s4 WHERE s4.userModel.userId = :userId AND s4.surveyModel.surveyId = :surveyId)")
    Integer findUserRankInSurvey(@Param("userId") Integer userId, @Param("surveyId") Integer surveyId);}
