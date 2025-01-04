package com.company.fantasyturnedreal.service.season;

import com.company.fantasyturnedreal.dto.season.CreateEpisodeRequest;
import com.company.fantasyturnedreal.dto.season.UpdateEpisodeRequest;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.season.Season;
import com.company.fantasyturnedreal.repository.season.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {

    @Autowired
    EpisodeRepository episodeRepo;

    @Autowired
    SeasonService seasonService;

    public List<Episode> getAllEpisodes() {
        return episodeRepo.findAll();
    }

    public List<Episode> getAllEpisodesInSeason(Long seasonId) {
        return episodeRepo.findBySeasonSeasonId(seasonId);
    }

    public Episode getEpisodeById(Long episodeId) {
        Episode foundEpisode = episodeRepo.findById(episodeId).orElse(null);
        if (foundEpisode == null) {
            throw new DataNotFoundException(String.format("Episode with id '%s' does not exist.", episodeId));
        }
        return foundEpisode;
    }

    public Episode createEpisode(CreateEpisodeRequest createEpisodeRequest) {
        Episode episode = new Episode();
        episode.setTitle(createEpisodeRequest.getTitle());
        episode.setSeason(seasonService.getSeasonById(createEpisodeRequest.getSeasonId()));
        episode.setAirDate(createEpisodeRequest.getAirDate());
        return episodeRepo.save(episode);
    }

    public void updateEpisodeDetails(UpdateEpisodeRequest updateEpisodeRequest) {
        Episode foundEpisode = getEpisodeById(updateEpisodeRequest.getEpisodeId());
        if (updateEpisodeRequest.getTitle() != null) {
            foundEpisode.setTitle(updateEpisodeRequest.getTitle());
        }
        if (updateEpisodeRequest.getAirDate() != null) {
            foundEpisode.setAirDate(updateEpisodeRequest.getAirDate());
        }
        if (updateEpisodeRequest.getSeasonId() != null) {
            Season season = seasonService.getSeasonById(updateEpisodeRequest.getSeasonId());
            foundEpisode.setSeason(season);
        }
        episodeRepo.save(foundEpisode);
    }

    public void deleteEpisode(Long episodeId) {
        Episode episode = getEpisodeById(episodeId);
        episodeRepo.deleteById(episodeId);
    }

}
