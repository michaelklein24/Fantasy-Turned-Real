package com.kleintwins.ftr.league.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetLeagueByIdResponse {
    private League league;

    public GetLeagueByIdResponse(League league) {
        this.league = league;
    }
}
