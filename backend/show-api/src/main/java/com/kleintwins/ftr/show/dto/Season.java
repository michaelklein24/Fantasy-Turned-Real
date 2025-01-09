package com.kleintwins.ftr.show.dto;

import com.kleintwins.ftr.show.code.Show;
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
