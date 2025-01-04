package com.company.fantasyturnedreal.service.season;

import com.company.fantasyturnedreal.dto.season.CreateSeasonRequest;
import com.company.fantasyturnedreal.dto.season.UpdateSeasonRequest;
import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.season.Season;
import com.company.fantasyturnedreal.repository.season.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService {
    @Autowired
    SeasonRepository seasonRepo;

    public List<Season> getAllSeasons() {
        return seasonRepo.findAll();
    }

    public List<Season> getAllSeasonByShow(Show show) {
        return seasonRepo.findByShow(show);
    }

    public Season getSeasonById(Long seasonId) {
        Season foundSeason = seasonRepo.findById(seasonId).orElse(null);
        if (foundSeason == null) {
            throw new DataNotFoundException(String.format("Season with id '%s' does not exist.", seasonId));
        }
        return foundSeason;
    }

    public Season createSeason(CreateSeasonRequest createSeasonRequest) {
        Season season = new Season();
        season.setTitle(createSeasonRequest.getTitle());
        season.setShow(createSeasonRequest.getShow());
        season.setSeasonNumber(createSeasonRequest.getSeasonNumber());
        return seasonRepo.save(season);
    }

    public void updateSeason(UpdateSeasonRequest updateSeasonRequest) {
        Season foundSeason = getSeasonById(updateSeasonRequest.getSeasonId());
        if (updateSeasonRequest.getSeasonNumber() != null) {
            foundSeason.setSeasonNumber(updateSeasonRequest.getSeasonNumber());
        }
        if (updateSeasonRequest.getShow() != null) {
            foundSeason.setShow(updateSeasonRequest.getShow());
        }
        if (updateSeasonRequest.getTitle() != null) {
            foundSeason.setTitle(updateSeasonRequest.getTitle());
        }
        seasonRepo.save(foundSeason);
    }

    public void deleteSeason(Long seasonId) {
        Season season = getSeasonById(seasonId);
        seasonRepo.deleteById(seasonId);
    }

}
