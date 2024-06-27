package com.ftr.api.league.dto;

import com.ftr.api.league.code.LeagueStatusCode;
import lombok.Data;

import java.util.List;

@Data
public class GetLeagueDetailsByIdForUserResponse {
    private String name;
    private List<ParticipantSummary> participants;
    private ParticipantSummary admin;
    private LeagueStatusCode status;
}
