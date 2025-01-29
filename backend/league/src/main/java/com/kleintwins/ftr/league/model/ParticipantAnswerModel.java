package com.kleintwins.ftr.league.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lge_participant_answer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantAnswerModel {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ParticipantAnswerId participantAnswerId;

    private Boolean correct;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "lge_provided_answers",
            joinColumns = {
                    @JoinColumn(name = "survey_id"),
                    @JoinColumn(name = "question_sequence"),
                    @JoinColumn(name = "user_id"),
                    @JoinColumn(name = "league_id")
            })
    @Builder.Default
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
