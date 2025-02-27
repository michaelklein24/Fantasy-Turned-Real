package com.kleintwins.ftr.show.util;

import com.kleintwins.ftr.show.dto.GetSeasonsResponse;
import com.kleintwins.ftr.show.dto.GetShowsResponse;
import com.kleintwins.ftr.show.dto.Season;
import com.kleintwins.ftr.show.dto.Show;
import com.kleintwins.ftr.show.model.SeasonModel;
import com.kleintwins.ftr.show.model.ShowModel;

import java.util.List;

public class SeasonResponseBuilder {

    public static GetShowsResponse buildGetShowsResponse(List<ShowModel> showModels) {
        GetShowsResponse response = new GetShowsResponse();
        response.setShows(showModels.stream().map(SeasonResponseBuilder::buildShow).toList());

        return response;
    }

    public static Show buildShow(ShowModel showModel) {
        Show show = new Show();
        show.setName(showModel.getShow());
        show.setSeasons(showModel.getSeasons().stream().map(SeasonResponseBuilder::buildSeason).toList());
        return show;
    }

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
