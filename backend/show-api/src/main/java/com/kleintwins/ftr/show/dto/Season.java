package com.kleintwins.ftr.show.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Season {
    private int sequence;
    private String show;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalEpisodes;
}
