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
public class AddUserToLeagueRequest {
    @NotNull(message = "userId must not be null")
    private Long userId;
    @NotNull(message = "leagueId must not be null")
    private Long leagueId;
}
