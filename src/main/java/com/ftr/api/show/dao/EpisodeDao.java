package com.ftr.api.show.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.show.model.EpisodeModel;
import com.ftr.api.show.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EpisodeDao implements IDaoImpl<EpisodeModel> {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public EpisodeModel saveEntity(EpisodeModel entity) {
        return episodeRepository.save(entity);
    }

    @Override
    public Optional<EpisodeModel> findEntityById(Integer id) {
        return episodeRepository.findById(id);
    }

    @Override
    public void updateEntity(EpisodeModel entity) {
        episodeRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        episodeRepository.deleteById(id);
    }
}
