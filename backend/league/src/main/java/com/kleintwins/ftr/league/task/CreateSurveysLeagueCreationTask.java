package com.kleintwins.ftr.league.task;

import com.kleintwins.ftr.league.code.SurveyType;
import com.kleintwins.ftr.league.event.LeagueSurveysInitializationEvent;
import com.kleintwins.ftr.league.model.LeagueModel;
import com.kleintwins.ftr.league.service.SurveyService;
import com.kleintwins.ftr.show.model.EpisodeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateSurveysLeagueCreationTask implements ILeagueCreationTask {

    private final ApplicationEventPublisher eventPublisher;
    
    @Override
    public void doWork(LeagueModel leagueModel) {
        eventPublisher.publishEvent(new LeagueSurveysInitializationEvent(leagueModel));
    }

}
