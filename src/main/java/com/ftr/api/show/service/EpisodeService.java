package com.ftr.api.show.service;

import com.ftr.api.show.model.EpisodeModel;
import com.ftr.api.show.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public Optional<EpisodeModel> getEpisodeByEpisodeId(Integer episodeId) {
        return this.episodeRepository.findById(episodeId);
    }
}
