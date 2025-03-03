package com.kleintwins.ftr.league.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetSurveysResponse {
    List<Survey> surveys;

    public GetSurveysResponse(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public GetSurveysResponse() {
    }
}
