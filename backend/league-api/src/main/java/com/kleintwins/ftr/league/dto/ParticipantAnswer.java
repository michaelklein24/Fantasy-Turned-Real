package com.kleintwins.ftr.league.dto;

import lombok.Data;

@Data
public class ParticipantAnswer {
    private String participantId;
    private Boolean correct;
    private String surveyId;
    private String questionSequence;
}
