package com.kleintwins.ftr.controller.league.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class League {
    private String leagueId;
    private String name;
    private List<Participant> participants;
}
