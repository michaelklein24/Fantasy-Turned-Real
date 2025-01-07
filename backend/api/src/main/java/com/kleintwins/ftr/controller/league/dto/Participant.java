package com.kleintwins.ftr.controller.league.dto;

import com.kleintwins.ftr.core.code.LeagueRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Participant {
    private String leagueId;
    private String userId;
    private LeagueRole role;
}
