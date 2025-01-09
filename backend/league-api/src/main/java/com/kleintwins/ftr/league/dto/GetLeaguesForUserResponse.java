package com.kleintwins.ftr.league.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GetLeaguesForUserResponse {
    private List<League> leagues = new ArrayList<>();

    public GetLeaguesForUserResponse(List<League> leagues) {
        this.leagues = leagues;
    }
}
