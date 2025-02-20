package com.kleintwins.ftr.show.util;

import com.kleintwins.ftr.show.dto.GetSeasonsResponse;
import com.kleintwins.ftr.show.dto.Season;
import com.kleintwins.ftr.show.model.SeasonModel;

import java.util.List;

public class SeasonResponseBuilder {

    public static GetSeasonsResponse buildGetSeasonsResponse(List<SeasonModel> seasonModels) {
        GetSeasonsResponse response = new GetSeasonsResponse();
        response.setSeasons(seasonModels.stream().map(SeasonResponseBuilder::buildSeason).toList());
        return response;
    }

    public static Season buildSeason(SeasonModel seasonModel) {
        return Season.builder()
                .sequence(seasonModel.getSeasonId().getSeasonSequence())
                .show(seasonModel.getSeasonId().getShow())
                .startTime(seasonModel.getStartTime())
                .endTime(seasonModel.getEndTime())
                .totalEpisodes(seasonModel.getTotalEpisodes())
                .build();
    }
}
