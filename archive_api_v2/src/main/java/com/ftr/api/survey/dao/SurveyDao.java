package com.ftr.api.survey.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.survey.model.SurveyModel;
import com.ftr.api.survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SurveyDao implements IDaoImpl<SurveyModel> {

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public SurveyModel saveEntity(SurveyModel entity) {
        return surveyRepository.save(entity);
    }

    @Override
    public Optional<SurveyModel> findEntityById(Integer id) {
        return surveyRepository.findById(id);
    }

    @Override
    public void updateEntity(SurveyModel entity) {
        surveyRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        surveyRepository.deleteById(id);
    }

    public boolean doesSurveyForLeagueExist(Integer episodeId, Integer leagueId) {
        return surveyRepository.existsByEpisodeModelEpisodeIdAndLeagueModelLeagueId(episodeId, leagueId);
    }
}
