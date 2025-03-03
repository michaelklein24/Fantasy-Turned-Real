package com.kleintwins.ftr.league.dto;

import com.kleintwins.ftr.league.code.SurveyStatus;
import com.kleintwins.ftr.league.code.SurveyType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Survey {
    private String id;
    private String name;
    private SurveyType type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Question> questions;
    private SurveyStatus status = SurveyStatus.OPEN;
    private int totalPoints;
    private int pointsEarned;
}
