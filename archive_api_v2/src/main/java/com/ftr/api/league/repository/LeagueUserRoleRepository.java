package com.ftr.api.league.repository;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.model.LeagueUserRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LeagueUserRoleRepository extends JpaRepository<LeagueUserRoleModel, Integer> {
    Optional<LeagueUserRoleModel> findByUserModelUserIdAndLeagueModelLeagueId(Integer userId, Integer leagueId);

    @Query("SELECT COUNT(p) FROM LeagueUserRoleModel p WHERE p.leagueModel.leagueId = :leagueId")
    Integer countLeagueUserRoleByLeagueId(@Param("leagueId") Integer leagueId);

    boolean existsByUserModelUserIdAndLeagueModelLeagueId(Integer userId, Integer leagueId);

    List<LeagueUserRoleModel> findByUserModelUserId(Integer userId);

    List<LeagueUserRoleModel> findByLeagueModelLeagueIdAndLeagueRole(Integer leagueId, LeagueRoleCode role);

    List<LeagueUserRoleModel> findByLeagueModelLeagueId(Integer leagueId);



}
