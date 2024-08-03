package com.ftr.api.league.dto;

import lombok.Data;

@Data
public class InviteDetails {
    private String leagueName;
    private Integer leagueId;
    private String invitedBy;
}
