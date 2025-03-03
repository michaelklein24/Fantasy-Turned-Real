package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.model.SurveyStatusId;
import com.kleintwins.ftr.league.model.SurveyStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyStatusRepository extends JpaRepository<SurveyStatusModel, SurveyStatusId> {
    List<SurveyStatusModel> findBySurveyStatusIdSurveyId(String surveyId);
}
