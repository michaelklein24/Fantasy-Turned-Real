package com.company.fantasyturnedreal.repository.league;

import com.company.fantasyturnedreal.model.league.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

}
