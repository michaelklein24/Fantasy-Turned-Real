package com.kleintwins.ftr.show.service;

import com.kleintwins.ftr.core.service.I18nService;
import com.kleintwins.ftr.show.model.SeasonId;
import com.kleintwins.ftr.show.model.SeasonModel;
import com.kleintwins.ftr.show.repository.SeasonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepo;
    private final I18nService i18nService;

    public SeasonModel getSeasonByShowAndSeasonId(String show, int sequence) {
        SeasonId id = new SeasonId(show, sequence);

        // Use `seasonRepo.findById(id)` directly
        return seasonRepo.findById(id).orElseThrow(() -> {
            String errorMsg = i18nService.translate(
                    "api.season.findSeasonBySeasonId.response.error.notFound.message",
                    String.valueOf(sequence),
                    String.valueOf(show)
            );
            return new EntityNotFoundException(errorMsg);
        });
    }

    public List<SeasonModel> getSeasonsForShow(String show) {
        return seasonRepo.findBySeasonIdShow(show);
    }


}
