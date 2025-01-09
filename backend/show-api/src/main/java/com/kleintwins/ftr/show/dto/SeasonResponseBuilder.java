package com.kleintwins.ftr.show.dto;

import com.kleintwins.ftr.show.model.SeasonModel;

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
