package com.kleintwins.ftr.league.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lge_correct_answers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CorrectAnswerModel {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private CorrectAnswerId correctAnswerId;

    @ManyToMany
    @JoinTable(
            name = "lge_correct_answer_option",
            joinColumns = {
                    @JoinColumn(name = "survey_id"),
                    @JoinColumn(name = "question_sequence")
            })
    private List<AnswerOptionModel> correctAnswers;

    @OneToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "survey_id", referencedColumnName = "survey_id", nullable = false),
            @JoinColumn(name = "question_sequence", referencedColumnName = "sequence", nullable = false)
    })
    private QuestionModel question;
}

