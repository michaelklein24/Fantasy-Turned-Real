package com.kleintwins.ftr.league.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ParticipantAnswerId {
    @Column(name = "survey_id", nullable = false)
    private String surveyId;

    @Column(name = "question_sequence", nullable = false)
    private Integer questionSequence;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "league_id", nullable = false)
    private String leagueId;

    public ParticipantAnswerId(String surveyId, Integer questionSequence, String userId, String leagueId) {
        this.surveyId = surveyId;
        this.questionSequence = questionSequence;
        this.userId = userId;
        this.leagueId = leagueId;
    }
}
