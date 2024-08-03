package com.ftr.api.league.repository;

import com.ftr.api.league.code.InviteStatusCode;
import com.ftr.api.league.model.InviteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InviteRepository extends JpaRepository<InviteModel, Integer> {
    List<InviteModel> findByEmailAndStatus(String email, InviteStatusCode status);
    Optional<InviteModel> findByEmailAndStatusAndLeagueModelLeagueId(String email, InviteStatusCode status, Integer leagueId);
}
