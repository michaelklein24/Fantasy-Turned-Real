package com.kleintwins.ftr.league.repository;

import com.kleintwins.ftr.league.model.LeagueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueModel, String> {
}
