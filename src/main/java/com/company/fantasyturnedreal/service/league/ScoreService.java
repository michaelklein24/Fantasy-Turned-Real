package com.company.fantasyturnedreal.service.league;

import com.company.fantasyturnedreal.model.league.Answer;
import com.company.fantasyturnedreal.model.league.League;
import com.company.fantasyturnedreal.model.league.Score;
import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.repository.league.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

    @Autowired
    ScoreRepository scoreRepo;

    //JSON EXAMPLE
    //{
    //    "User1": 100,
    //    "User2": 75,
    //    "User3": 120
    //}
    public Map<User, Integer> getTotalScoresForLeague(Long leagueId) {
        List<Score> scores = scoreRepo.findByLeagueLeagueId(leagueId);
        Map<User, Integer> totalScores = new HashMap<>();

        for (Score score : scores) {
            User user = score.getUser();
            int pointsEarned = score.getTotalPoints();

            // Accumulate points for each user
            totalScores.put(user, totalScores.getOrDefault(user, 0) + pointsEarned);
        }

        return totalScores;
    }

    //JSON EXAMPLE
    //{
    //    "User1": {
    //        "TotalScores": {
    //            "Episode1": 20,
    //            "Episode2": 50,
    //            "Episode3": 90
    //        },
    //        "IndividualScores": {
    //            "Episode1": 20,
    //            "Episode2": 30,
    //            "Episode3": 40
    //        }
    //    },
    //    "User2": {
    //        "TotalScores": {
    //            "Episode1": 10,
    //            "Episode2": 40,
    //            "Episode3": 70
    //        },
    //        "IndividualScores": {
    //            "Episode1": 10,
    //            "Episode2": 20,
    //            "Episode3": 30
    //        }
    //    },
    //    "User3": {
    //        "TotalScores": {
    //            "Episode1": 30,
    //            "Episode2": 60,
    //            "Episode3": 90
    //        },
    //        "IndividualScores": {
    //            "Episode1": 30,
    //            "Episode2": 40,
    //            "Episode3": 50
    //        }
    //    }
    //}
    public Map<User, Map<String, Map<Episode, Integer>>> getIndividualAndCombinedScoresPerEpisodeForLeague(Long leagueId) {
        List<Score> scores = scoreRepo.findByLeagueLeagueId(leagueId);
        Map<User, Map<String, Map<Episode, Integer>>> combinedScores = new HashMap<>();

        for (Score score : scores) {
            User user = score.getUser();
            Episode episode = score.getEpisode();
            int totalPointsEarned = score.getTotalPoints();
            int pointsEarnedInEpisode = score.getPointsEarnedInEpisode();

            // Get or create a user entry
            combinedScores.computeIfAbsent(user, k -> new HashMap<>());

            // Get or create TotalScores and IndividualScores entries
            combinedScores.get(user).computeIfAbsent("TotalScores", k -> new HashMap<>());
            combinedScores.get(user).computeIfAbsent("IndividualScores", k -> new HashMap<>());

            // Update TotalScores for the episode
            combinedScores.get(user).get("TotalScores").put(episode, totalPointsEarned);

            // Update IndividualScores for the episode
            combinedScores.get(user).get("IndividualScores").put(episode, pointsEarnedInEpisode);
        }

        return combinedScores;
    }

    //JSON EXAMPLE
    //100
    public Integer getTotalScoresForUserInLeague(Long leagueId, Long userId) {
        // Retrieve the total scores for a specific user in the league
        List<Score> userScores = scoreRepo.findByLeagueLeagueIdAndUserUserId(leagueId, userId);

        int totalScores = 0;
        for (Score score : userScores) {
            totalScores += score.getTotalPoints();
        }

        return totalScores;
    }

    //JSON EXAMPLE
    //{
    //    "Episode1": 20,
    //    "Episode2": 30,
    //    "Episode3": 40
    //}
    public Map<Episode, Integer> getIndividualScoresForUserInLeague(Long leagueId, Long userId) {
        // Retrieve individual scores for a specific user in the league
        List<Score> userScores = scoreRepo.findByLeagueLeagueIdAndUserUserId(leagueId, userId);

        Map<Episode, Integer> individualScores = new HashMap<>();

        for (Score score : userScores) {
            Episode episode = score.getEpisode();
            int pointsEarnedInEpisode = score.getPointsEarnedInEpisode();
            individualScores.put(episode, pointsEarnedInEpisode);
        }

        return individualScores;
    }

    //JSON EXAMPLE
    //{
    //    "User1": {
    //        "TotalScores": 20,
    //        "IndividualScores": 20
    //    },
    //    "User2": {
    //        "TotalScores": 10,
    //        "IndividualScores": 10
    //    },
    //    "User3": {
    //        "TotalScores": 30,
    //        "IndividualScores": 30
    //    }
    //}
    public Map<User, Map<String, Integer>> getAllScoresForEpisodeInLeague(Long leagueId, Long episodeId) {
        // Retrieve all scores (total and individual) for a specific episode in the league
        List<Score> episodeScores = scoreRepo.findByLeagueLeagueIdAndUserUserId(leagueId, episodeId);

        Map<User, Map<String, Integer>> allScoresForEpisode = new HashMap<>();

        for (Score score : episodeScores) {
            User user = score.getUser();
            int totalPoints = score.getTotalPoints();
            int pointsEarnedInEpisode = score.getPointsEarnedInEpisode();

            // Get or create a map for the user's scores for the episode
            Map<String, Integer> userScoresForEpisode = allScoresForEpisode.computeIfAbsent(user, k -> new HashMap<>());

            // Add total and individual scores to the user's map
            userScoresForEpisode.put("TotalScores", totalPoints);
            userScoresForEpisode.put("IndividualScores", pointsEarnedInEpisode);
        }

        return allScoresForEpisode;
    }

    public Score calculateScore(Answer answer) {
        User user = answer.getUser();
        League league = answer.getQuestion().getLeague();
        Episode episode = answer.getQuestion().getEpisode();

        Score score = scoreRepo.findByUserAndLeagueAndEpisode(user, league, episode);

        if (score == null) {
            score = new Score();
            score.setUser(user);
            score.setLeague(league);
            score.setEpisode(episode);
            score.setAnswer(answer);
        }

        int pointsEarned = answer.getIsCorrect() ? answer.getQuestion().getPoints() : 0;

        score.setPointsEarnedInEpisode(score.getPointsEarnedInEpisode() + pointsEarned);
        score.setTotalPoints(score.getTotalPoints() + pointsEarned);

        return score;
    }

}
