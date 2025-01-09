package com.kleintwins.ftr.league.dto;

import com.kleintwins.ftr.show.dto.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class League {
    private String leagueId;
    private String name;
    private List<Participant> participants;
    private Season season;
    private LocalDateTime createTime;
}
