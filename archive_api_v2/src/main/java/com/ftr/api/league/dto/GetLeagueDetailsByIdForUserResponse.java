package com.ftr.api.league.dto;

import com.ftr.api.league.code.LeagueStatusCode;
import lombok.Data;

import java.util.List;

@Data
public class GetLeagueDetailsByIdForUserResponse {
    private String name;
    private List<LeagueUserSummary> leagueUserSummaries;
    private LeagueUserSummary admin;
    private LeagueStatusCode status;
}
