package com.company.fantasyturnedreal.service.season;

import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.model.season.Season;
import com.company.fantasyturnedreal.repository.season.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonService {
    @Autowired
    SeasonRepository seasonRepo;

    public Season getSeasonById(Long seasonId) {
        Season foundSeason = seasonRepo.findById(seasonId).orElse(null);
        if (foundSeason == null) {
            throw new DataNotFoundException(String.format("Season with id '%s' does not exist.", seasonId));
        }
        return foundSeason;
    }
}
