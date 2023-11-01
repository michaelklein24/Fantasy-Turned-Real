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
public class CreateLeagueRequest {
    @NotNull(message = "seasonId cannot be null")
    private Long seasonId;
    @NotNull(message = "adminUserId cannot be null")
    private Long adminUserId;
    @NotNull(message = "leagueName cannot be null")
    private String leagueName;
}
