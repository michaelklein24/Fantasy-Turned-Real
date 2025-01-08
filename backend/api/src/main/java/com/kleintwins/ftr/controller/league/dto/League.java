package com.kleintwins.ftr.controller.league.dto;

import com.kleintwins.ftr.controller.season.dto.Season;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class League {
    private String leagueId;
    private String name;
    private List<Participant> participants;
    private Season season;
    private LocalDateTime createTime;
}
