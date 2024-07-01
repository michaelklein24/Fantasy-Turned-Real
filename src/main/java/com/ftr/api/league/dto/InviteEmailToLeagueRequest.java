package com.ftr.api.league.dto;

import lombok.Data;

import java.util.Set;

@Data
public class InviteEmailToLeagueRequest {
    private Integer leagueId;
    private Set<String> emails;
}
