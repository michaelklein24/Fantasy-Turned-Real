package com.kleintwins.ftr.controller.league.dto;

import com.kleintwins.ftr.core.code.Show;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private Show show;

    public CreateLeagueRequest() {}

    public CreateLeagueRequest(String name) {
        this.name = name;
    }
}
