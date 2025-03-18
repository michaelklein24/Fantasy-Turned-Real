package com.kleintwins.ftr.league.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lge_participant_answer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ParticipantAnswerModel {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ParticipantAnswerId participantAnswerId;

    private Boolean correct;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "lge_provided_answers",
            joinColumns = {
                    @JoinColumn(name = "survey_id", columnDefinition = "varchar(255)"),
                    @JoinColumn(name = "question_sequence", columnDefinition = "integer"),
                    @JoinColumn(name = "user_id", columnDefinition = "varchar(255)"),
                    @JoinColumn(name = "league_id", columnDefinition = "varchar(255)")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "sequence", columnDefinition = "integer", insertable = false, updatable = false),
                    @JoinColumn(name = "question_sequence", columnDefinition = "integer", insertable = false, updatable = false),
                    @JoinColumn(name = "survey_id", columnDefinition = "varchar(255)", insertable= false, updatable = false),
            }
    )
    private List<AnswerOptionModel> providedAnswers = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "survey_id", referencedColumnName = "survey_id", nullable = false, updatable = false, insertable = false),
            @JoinColumn(name = "question_sequence", referencedColumnName = "sequence", nullable = false, updatable = false, insertable = false)
    })
    private QuestionModel question;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false),
            @JoinColumn(name = "league_id", referencedColumnName = "league_id", nullable = false, updatable = false, insertable = false)
    })
    private ParticipantModel participant;
}
