package com.ftr.api.survey.repository;

import com.ftr.api.survey.model.SurveyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyModel, Integer> {
    boolean existsByEpisodeModelEpisodeIdAndLeagueModelLeagueId(Integer episodeId, Integer leagueId);
}
