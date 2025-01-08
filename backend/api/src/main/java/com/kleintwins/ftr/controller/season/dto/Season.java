package com.kleintwins.ftr.controller.season.dto;

import com.kleintwins.ftr.core.code.Show;
import com.kleintwins.ftr.core.model.SeasonId;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Season {
    private int sequence;
    private Show show;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalEpisodes;
}
