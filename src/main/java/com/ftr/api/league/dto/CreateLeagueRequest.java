package com.ftr.api.league.dto;

import lombok.Data;

@Data
public class CreateLeagueRequest {
    private String name;
    private String showId;
    private Integer userId;
}
