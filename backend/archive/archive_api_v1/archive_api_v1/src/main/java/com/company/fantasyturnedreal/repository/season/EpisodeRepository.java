package com.company.fantasyturnedreal.repository.season;

import com.company.fantasyturnedreal.model.season.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findBySeasonSeasonId(Long seasonId);
}
