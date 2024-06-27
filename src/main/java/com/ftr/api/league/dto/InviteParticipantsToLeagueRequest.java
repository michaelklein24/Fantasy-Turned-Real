package com.ftr.api.league.dto;

import lombok.Data;

import java.util.Set;

@Data
public class InviteParticipantsToLeagueRequest {
    private Integer leagueId;
    private Set<String> emails;
}
