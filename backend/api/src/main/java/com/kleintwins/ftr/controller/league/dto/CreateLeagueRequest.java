package com.kleintwins.ftr.controller.league.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateLeagueRequest {
    @NotBlank(message = "League name is required.")
    private String name;

    public CreateLeagueRequest() {}

    public CreateLeagueRequest(String name) {
        this.name = name;
    }
}
