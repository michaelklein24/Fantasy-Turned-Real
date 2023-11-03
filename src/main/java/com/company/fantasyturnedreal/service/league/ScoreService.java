package com.company.fantasyturnedreal.service.league;

import com.company.fantasyturnedreal.model.league.Answer;
import com.company.fantasyturnedreal.model.league.League;
import com.company.fantasyturnedreal.model.league.Score;
import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.repository.league.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

    @Autowired
    ScoreRepository scoreRepo;

    public Map<User, Integer> getTotalScoresForLeague(League league) {
        List<Score> scores = scoreRepo.findByLeague(league);
        Map<User, Integer> totalScores = new HashMap<>();

        for (Score score : scores) {
            User user = score.getUser();
            int pointsEarned = score.getTotalPoints();

            // Accumulate points for each user
            totalScores.put(user, totalScores.getOrDefault(user, 0) + pointsEarned);
        }

        return totalScores;
    }

    public Map<User, Map<Episode, Integer>> getTotalScoresAfterEachEpisode(League league) {
        List<Score> scores = scoreRepo.findByLeague(league);
        Map<User, Map<Episode, Integer>> scoresAfterEachEpisode = new HashMap<>();

        for (Score score : scores) {
            User user = score.getUser();
            Episode episode = score.getEpisode();
            int pointsEarned = score.getTotalPoints();

            // Get or create a map for the user's scores after each episode
            scoresAfterEachEpisode.computeIfAbsent(user, k -> new HashMap<>());

            // Accumulate points for the user and episode
            scoresAfterEachEpisode.get(user).put(episode, pointsEarned);
        }

        return scoresAfterEachEpisode;
    }

    public Map<User, Map<Episode, Integer>> getIndividualScoresForEachEpisode(League league) {
        List<Score> scores = scoreRepo.findByLeague(league);
        Map<User, Map<Episode, Integer>> individualScoresForEachEpisode = new HashMap<>();

        for (Score score : scores) {
            User user = score.getUser();
            Episode episode = score.getEpisode();
            int pointsEarned = score.getPointsEarnedInEpisode();

            // Get or create a map for the user's individual scores for each episode
            individualScoresForEachEpisode.computeIfAbsent(user, k -> new HashMap<>());

            // Store the points earned for the episode
            individualScoresForEachEpisode.get(user).put(episode, pointsEarned);
        }

        return individualScoresForEachEpisode;
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
