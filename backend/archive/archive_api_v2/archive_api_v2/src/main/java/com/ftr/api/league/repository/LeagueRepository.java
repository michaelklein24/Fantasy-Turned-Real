package com.ftr.api.league.repository;

import com.ftr.api.league.model.LeagueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueModel, Integer> {
}
