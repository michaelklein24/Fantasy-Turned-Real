package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.code.SurveyStatus;
import com.kleintwins.ftr.league.model.SurveyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyModel, String> {
    List<SurveyModel> findAllByLeagueLeagueId(String leagueId);

    @Query("SELECT s FROM SurveyModel s " +
            "JOIN s.statuses st " +
            "WHERE st.surveyStatusId.status = 'CLOSED' " +
            "AND s.startTime <= :currentTime " +
            "AND s.endTime > :currentTime " +
            "AND st.createTime = (" +
            "   SELECT MAX(st2.createTime) " +
            "   FROM SurveyStatusModel st2 " +
            "   WHERE st2.survey = s" +
            ")")
    List<SurveyModel> findClosedSurveysToBeOpened(
            @Param("currentTime") LocalDateTime currentTime);

    @Query("SELECT s FROM SurveyModel s " +
            "JOIN s.statuses st " +
            "WHERE st.surveyStatusId.status = 'OPEN' " +
            "AND s.endTime <= :currentTime " +
            "AND st.createTime = (" +
            "   SELECT MAX(st2.createTime) " +
            "   FROM s.statuses st2" +
            ")")
    List<SurveyModel> findOpenedSurveysToBePending(
            @Param("currentTime") LocalDateTime currentTime);

}
