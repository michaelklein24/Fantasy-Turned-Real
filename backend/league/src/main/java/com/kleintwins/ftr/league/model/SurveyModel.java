package com.kleintwins.ftr.league.model;

import com.kleintwins.ftr.core.exception.EntityNotFound;
import com.kleintwins.ftr.league.code.SurveyStatus;
import com.kleintwins.ftr.league.code.SurveyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "lge_survey")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SurveyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String surveyId;

    @Column(nullable = false, length = 255)
    @NotNull(message = "Survey name cannot be null")
    @Size(max = 255, message = "Survey name must be at most 255 characters")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private SurveyType type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime startTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime endTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyStatusModel> statuses = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<QuestionModel> questions = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "league_id", nullable = false)
    private LeagueModel league;

    public SurveyModel(LeagueModel league, String name, SurveyType type, LocalDateTime startTime, LocalDateTime endTime, List<QuestionModel> questions) {
        this.league = league;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questions = questions;
    }

    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    public void addQuestion(QuestionModel question) {
        questions.add(question);
        question.setSurvey(this);
    }

    public void removeQuestion(QuestionModel question) {
        questions.remove(question);
        question.setSurvey(null);
    }

    public SurveyStatusModel getCurrentStatus() {
        return statuses.stream().max(Comparator.comparing(SurveyStatusModel::getCreateTime))
                .orElseThrow(() -> new EntityNotFound("Survey Status was not found"));
    }
}

