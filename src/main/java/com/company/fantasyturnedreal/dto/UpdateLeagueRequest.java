package com.company.fantasyturnedreal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLeagueRequest {
    @NotNull(message = "leagueId cannot be null")
    private Long leagueId;
    private String leagueName;

}
