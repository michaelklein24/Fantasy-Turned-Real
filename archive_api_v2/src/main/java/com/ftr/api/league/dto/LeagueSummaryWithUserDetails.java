package com.ftr.api.league.dto;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.code.LeagueStatusCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LeagueSummaryWithUserDetails {
    private Integer userId;
    private Integer leagueId;
    private LeagueRoleCode role;
    private BigDecimal totalPoints;
    private Integer totalNumberOfPlayers;
    private Integer placement;
    private String name;
    private LeagueStatusCode status;
}
