package com.ftr.api.league.dto;

import com.ftr.api.league.code.LeagueRoleCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParticipantSummary {
    private Integer userId;
    private String firstName;
    private String lastName;
    private LeagueRoleCode role;
    private BigDecimal totalPoints;
    private Integer placement;
}
