package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.model.SurveyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyModel, String> {
}
