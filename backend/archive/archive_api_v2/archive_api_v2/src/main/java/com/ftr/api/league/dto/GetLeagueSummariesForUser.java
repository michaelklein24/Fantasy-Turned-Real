package com.ftr.api.league.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetLeagueSummariesForUser {
    private List<LeagueSummaryWithUserDetails> currentLeagues;
    private List<LeagueSummaryWithUserDetails> completedLeagues;
}
