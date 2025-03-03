package com.kleintwins.ftr.league.event;

import com.kleintwins.ftr.league.model.LeagueModel;
import lombok.Data;

@Data
public class LeagueSurveysInitializationEvent {
    private LeagueModel leagueModel;

    public LeagueSurveysInitializationEvent(LeagueModel leagueModel) {
        this.leagueModel = leagueModel;
    }

    public LeagueSurveysInitializationEvent() {
    }
}
