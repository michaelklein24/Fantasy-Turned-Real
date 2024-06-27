package com.ftr.api.survey.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoringDetails {
    private BigDecimal pointsAwarded;
    private BigDecimal totalPossiblePoints;
    private BigDecimal percentageObtained;
    private Integer placement;
    private Integer totalNumberOfPlayers;
}
