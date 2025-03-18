package com.kleintwins.ftr.league.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lge_answer_options")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AnswerOptionModel {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private AnswerOptionId answerOptionId;

    @Column(nullable = false, length = 500)
    @NotNull(message = "Answer option text cannot be null")
    @Size(max = 500, message = "Answer option text must be at most 500 characters")
    private String text;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "survey_id", referencedColumnName = "survey_id", nullable = false, updatable = false, insertable = false),
            @JoinColumn(name = "question_sequence", referencedColumnName = "sequence", nullable = false, updatable = false, insertable = false)
    })
    private QuestionModel question;
}
