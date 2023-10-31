package com.company.fantasyturnedreal.repository;

import com.company.fantasyturnedreal.model.season.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
