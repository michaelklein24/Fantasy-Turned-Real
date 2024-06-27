package com.ftr.api.score.model;

import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.score.code.PointSourceCode;
import com.ftr.api.survey.model.QuestionModel;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "lge_score")
public class ScoreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scoreId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "league_id", referencedColumnName = "leagueId")
    private LeagueModel leagueModel;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "participant_id", referencedColumnName = "participant_id")
    private ParticipantModel participantModel;

    private BigDecimal pointsAwarded;

    @Enumerated(value = EnumType.STRING)
    private PointSourceCode sourceCode;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "questionId")
    private QuestionModel questionModel;
}