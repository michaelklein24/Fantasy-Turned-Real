package com.company.fantasyturnedreal.service.league;

import com.company.fantasyturnedreal.model.league.Answer;
import com.company.fantasyturnedreal.model.league.Score;
import com.company.fantasyturnedreal.model.user.User;
import com.company.fantasyturnedreal.repository.league.ScoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ScoreService {

    @Autowired
    ScoreRepository scoreRepo;

    private final String INDIVIDUAL_SCORES = "IndividualScores";
    private final String TOTAL_SCORES = "TotalScores";

    public Map<Long, Integer> getTotalScoresForLeague(Long leagueId) {
        List<Score> scores = scoreRepo.findByLeagueLeagueId(leagueId);

        return scores.stream()
                .collect(Collectors.toMap(
                        score -> score.getUser().getUserId(),
                        Score::getPointsEarned,
                        Integer::sum
                ));
    }

    public Map<Long, Map<String, Map<Long, Integer>>> getIndividualAndTotalScoresPerEpisodeForLeague(Long leagueId) {
        List<Score> scores = scoreRepo.findByLeagueLeagueId(leagueId);

        Map<Long, Map<String, Map<Long, Integer>>> combinedScores = new HashMap<>();

        scores.forEach(score -> {
            Long userId = score.getUser().getUserId();
            Long episodeId = score.getEpisode().getEpisodeId();
            int pointsEarnedFromIndividualEpisode = score.getPointsEarned();

            combinedScores.computeIfAbsent(userId, k -> new HashMap<>());

            combinedScores.get(userId)
                    .computeIfAbsent(TOTAL_SCORES, k -> new HashMap<>())
                    .merge(episodeId, pointsEarnedFromIndividualEpisode, Integer::sum);

            combinedScores.get(userId)
                    .computeIfAbsent(INDIVIDUAL_SCORES, k -> new HashMap<>())
                    .merge(episodeId, pointsEarnedFromIndividualEpisode, Integer::sum);
        });

        // Accumulate the total scores across episodes
        combinedScores.forEach((userId, userScores) -> {
            userScores.get(TOTAL_SCORES).forEach((episodeId, totalScore) -> {
                int accumulatedTotalScore = userScores.get(TOTAL_SCORES).entrySet().stream()
                        .filter(entry -> entry.getKey() <= episodeId)
                        .mapToInt(Map.Entry::getValue)
                        .sum();
                userScores.get(TOTAL_SCORES).put(episodeId, accumulatedTotalScore);
            });
        });

        return combinedScores;
    }

    public Integer getTotalScoresForUserInLeague(Long leagueId, Long userId) {
        List<Score> userScores = scoreRepo.findByLeagueLeagueIdAndUserUserId(leagueId, userId);

        return userScores.stream()
                .map(Score::getPointsEarned)
                .reduce(0, Integer::sum);
    }

    public Map<Long, Integer> getIndividualScoresForUserInLeague(Long leagueId, Long userId) {
        List<Score> userScores = scoreRepo.findByLeagueLeagueIdAndUserUserId(leagueId, userId);

        return userScores.stream()
                .collect(Collectors.toMap(
                        score -> score.getEpisode().getEpisodeId(),
                        Score::getPointsEarned,
                        Integer::sum
                ));
    }

    public Score addScore(Answer answer) {
        User user = answer.getUser();
        Score score = scoreRepo.findByUserAndLeagueAndEpisode(user, answer.getQuestion().getLeague(), answer.getQuestion().getEpisode());

        if (score == null) {
            score = new Score();
            score.setUser(user);
            score.setLeague(answer.getQuestion().getLeague());
            score.setEpisode(answer.getQuestion().getEpisode());
        }

        int pointsEarned = answer.getIsCorrect() ? answer.getQuestion().getPoints() : 0;
        score.setPointsEarned(pointsEarned);

        return score;
    }
}
