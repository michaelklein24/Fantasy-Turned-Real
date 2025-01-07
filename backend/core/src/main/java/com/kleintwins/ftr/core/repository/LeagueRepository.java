package com.kleintwins.ftr.core.repository;

import com.kleintwins.ftr.core.model.LeagueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueModel, String> {
}
