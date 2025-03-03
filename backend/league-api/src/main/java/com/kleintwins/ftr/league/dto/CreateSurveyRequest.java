package com.kleintwins.ftr.league.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateSurveyRequest {
    private String leagueId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Question> questions;
}
