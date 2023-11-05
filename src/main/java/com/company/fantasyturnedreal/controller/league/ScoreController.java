package com.company.fantasyturnedreal.controller.league;

import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.repository.league.ScoreRepository;
import com.company.fantasyturnedreal.service.league.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@RestController
@RequestMapping(REST_API_CONTEXT_PATH + "/score/league/{leagueId}")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, Integer> getTotalScoresForLeague(@PathVariable Long leagueId) {
        return scoreService.getTotalScoresForLeague(leagueId);
    }

    @GetMapping("/episode")
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, Map<String, Map<Long, Integer>>> getTotalScoresAfterEachEpisodeForLeague(@PathVariable Long leagueId) {
        return scoreService.getIndividualAndTotalScoresPerEpisodeForLeague(leagueId);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getTotalScoresForUserInLeague(@PathVariable Long leagueId, @PathVariable Long userId) {
        return scoreService.getTotalScoresForUserInLeague(leagueId, userId);
    }

    @GetMapping("/user/{userId}/episode")
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, Integer> getIndividualScoresForUserInLeague(@PathVariable Long leagueId, @PathVariable Long userId) {
        return scoreService.getIndividualScoresForUserInLeague(leagueId, userId);
    }
}
