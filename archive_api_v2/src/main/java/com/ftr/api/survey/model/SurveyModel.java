package com.ftr.api.survey.model;

import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.show.model.EpisodeModel;
import com.ftr.api.survey.code.SurveyStatusCode;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lge_survey")
public class SurveyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer surveyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "league_id", referencedColumnName = "leagueId")
    private LeagueModel leagueModel;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "episode_id", referencedColumnName = "episodeId")
    private EpisodeModel episodeModel;

    @Enumerated(value = EnumType.STRING)
    private SurveyStatusCode status;
}
