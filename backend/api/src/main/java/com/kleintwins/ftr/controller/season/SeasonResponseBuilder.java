package com.kleintwins.ftr.controller.season;

import com.kleintwins.ftr.controller.season.dto.Season;
import com.kleintwins.ftr.core.model.SeasonModel;

public class SeasonResponseBuilder {

    public static Season buildSeason(SeasonModel seasonModel) {
        return Season.builder()
                .sequence(seasonModel.getSeasonId().getSequence())
                .show(seasonModel.getSeasonId().getShow())
                .startTime(seasonModel.getStartTime())
                .endTime(seasonModel.getEndTime())
                .totalEpisodes(seasonModel.getTotalEpisodes())
                .build();
    }
}
