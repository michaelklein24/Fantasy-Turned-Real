package com.kleintwins.ftr.league.event;

import com.kleintwins.ftr.league.code.SurveyType;
import com.kleintwins.ftr.league.model.LeagueModel;
import com.kleintwins.ftr.league.service.SurveyService;
import com.kleintwins.ftr.show.model.EpisodeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeagueEventListener {
    private final SurveyService surveyService;

    @EventListener
    public void handleLeagueCreated(LeagueSurveysInitializationEvent event) {
        LeagueModel leagueModel = event.getLeagueModel();
        for (EpisodeModel episodeModel : leagueModel.getSeason().getEpisodes()) {
            surveyService.createSurvey(
                    leagueModel,
                    String.format("Episode %d - %s", episodeModel.getEpisodeId().getEpisodeSequence(), episodeModel.getName()),
                    SurveyType.EPISODE,
                    episodeModel.getAirTime().minusDays(5),
                    episodeModel.getAirTime(),
                    null
            );
        }
    }
}
