package com.kleintwins.ftr.core.service;

import com.kleintwins.ftr.core.code.Show;
import com.kleintwins.ftr.core.model.SeasonId;
import com.kleintwins.ftr.core.model.SeasonModel;
import com.kleintwins.ftr.core.repository.SeasonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepo;
    private final I18nService i18nService;

    public SeasonModel findByShowAndSeasonId(Show show, int sequence) {
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


}
