package com.ftr.api.league.dto;

import lombok.Data;

@Data
public class CreateLeagueRequest {
    private String name;
    private Integer showId;
    private Integer userId;
}
