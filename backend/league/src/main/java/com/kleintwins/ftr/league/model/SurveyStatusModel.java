package com.kleintwins.ftr.league.model;

import jakarta.persistence.*;
import jdk.jfr.Experimental;
import lombok.*;
import lombok.experimental.Delegate;

import java.time.LocalDateTime;

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
    @Delegate
    private SurveyStatusId surveyStatusId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "surveyId", referencedColumnName = "surveyId", insertable = false, updatable = false)
    private SurveyModel survey;

    public SurveyStatusModel(SurveyStatusId surveyStatusId, SurveyModel survey) {
        this.surveyStatusId = surveyStatusId;
        this.survey = survey;
    }

    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }
}
