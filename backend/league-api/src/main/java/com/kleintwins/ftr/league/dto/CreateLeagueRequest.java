package com.kleintwins.ftr.league.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateLeagueRequest {
    @NotBlank(message = "League name is required.")
    private String name;
    @NotNull(message = "Season sequence number is required")
    private int seasonSequence;
    @NotNull(message = "Show is required")
    private String show;

    public CreateLeagueRequest() {}

    public CreateLeagueRequest(String name) {
        this.name = name;
    }
}
