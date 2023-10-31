package com.company.fantasyturnedreal.repository;

import com.company.fantasyturnedreal.model.season.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
