package com.kleintwins.ftr.league.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLeagueResponse {
    private League league;

    public CreateLeagueResponse(League league) {
        this.league = league;
    }
}
