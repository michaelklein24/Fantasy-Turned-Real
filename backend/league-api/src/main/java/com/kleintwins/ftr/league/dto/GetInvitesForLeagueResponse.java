package com.kleintwins.ftr.league.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetInvitesForLeagueResponse {
    private List<Invite> approved = new ArrayList<>();
    private List<Invite> pending = new ArrayList<>();
    private List<Invite> declined = new ArrayList<>();
}
