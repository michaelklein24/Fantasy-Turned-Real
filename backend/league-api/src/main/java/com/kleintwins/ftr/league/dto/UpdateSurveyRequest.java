package com.kleintwins.ftr.league.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateSurveyRequest {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public UpdateSurveyRequest(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UpdateSurveyRequest() {
    }
}
