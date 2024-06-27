package com.ftr.api.survey.repository;

import com.ftr.api.survey.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionModel, Integer> {
    List<QuestionModel> findBySurveyModelSurveyId(Integer surveyId);

    @Query("SELECT SUM(q.points) FROM QuestionModel q " +
            "WHERE q.surveyModel.surveyId = :surveyId")
    BigDecimal findSumOfPotentialPointsForQuestionsInSurvey(@Param("userId") Integer surveyId);
}
