package com.ftr.api.league.repository;

import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.model.ParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<ParticipantModel, Integer> {
    Optional<ParticipantModel> findByUserModelUserIdAndLeagueModelLeagueId(Integer userId, Integer leagueId);

    @Query("SELECT COUNT(p) FROM ParticipantModel p WHERE p.leagueModel.leagueId = :leagueId")
    Integer countParticipantsByLeagueId(@Param("leagueId") Integer leagueId);

    boolean existsByUserModelUserIdAndLeagueModelLeagueId(Integer userId, Integer leagueId);

    List<ParticipantModel> findByUserModelUserId(Integer userId);

    List<ParticipantModel> findByLeagueModelLeagueIdAndLeagueRole(Integer leagueId, LeagueRoleCode role);

    List<ParticipantModel> findByLeagueModelLeagueId(Integer leagueId);



}
