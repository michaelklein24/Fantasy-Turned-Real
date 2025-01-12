package com.kleintwins.ftr.league.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetInvitesForLeagueResponse {
    private List<Invite> invites = new ArrayList<>();
}
