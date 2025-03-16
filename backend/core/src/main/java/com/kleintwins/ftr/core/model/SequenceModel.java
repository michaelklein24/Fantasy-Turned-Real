package com.kleintwins.ftr.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "lge_correct_answers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SequenceModel {
    @EmbeddedId
    private SequenceId sequenceId;

    @Column(nullable = false)
    private Integer nextAvailableSequence;
}
