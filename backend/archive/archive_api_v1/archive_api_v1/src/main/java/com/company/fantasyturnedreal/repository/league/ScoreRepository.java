package com.company.fantasyturnedreal.repository.league;

import com.company.fantasyturnedreal.model.league.League;
import com.company.fantasyturnedreal.model.league.Score;
import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>, JpaSpecificationExecutor<Score> {
    Score findByUserAndLeagueAndEpisode(User user, League league, Episode episode);
    List<Score> findByLeagueLeagueIdAndUserUserId(Long leagueId, Long userId);
    List<Score> findByLeagueLeagueIdAndEpisodeEpisodeId(Long leagueId, Long episodeId);
    List<Score> findByLeagueLeagueId(Long leagueId);

}
