package com.kleintwins.ftr.league.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lge_survey_status")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SurveyStatusModel {
    @EmbeddedId
    private SurveyStatusId surveyStatusId;

    @ManyToOne(cascade = CascadeType.ALL)
    private SurveyModel survey;
}
