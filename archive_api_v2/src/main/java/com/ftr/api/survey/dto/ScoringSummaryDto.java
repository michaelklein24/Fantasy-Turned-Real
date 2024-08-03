package com.ftr.api.survey.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoringSummaryDto {
    private BigDecimal pointsAwarded;
    private BigDecimal totalPossiblePoints;
    private BigDecimal percentageObtained;
    private Integer placement;
    private Integer totalNumberOfPlayers;
}
