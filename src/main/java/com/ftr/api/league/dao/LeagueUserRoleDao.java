package com.ftr.api.league.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.model.LeagueUserRoleModel;
import com.ftr.api.league.repository.LeagueUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueUserRoleDao implements IDaoImpl<LeagueUserRoleModel> {

    @Autowired
    private LeagueUserRoleRepository leagueUserRoleRepository;

    @Override
    public LeagueUserRoleModel saveEntity(LeagueUserRoleModel entity) {
        return leagueUserRoleRepository.save(entity);
    }

    @Override
    public Optional<LeagueUserRoleModel> findEntityById(Integer id) {
        return leagueUserRoleRepository.findById(id);
    }

    @Override
    public void updateEntity(LeagueUserRoleModel entity) {
        leagueUserRoleRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        leagueUserRoleRepository.deleteById(id);
    }


    public Optional<LeagueUserRoleModel> findLeagueUserRoleByUserIdAndLeagueId(Integer userId, Integer leagueId) {
        return leagueUserRoleRepository.findByUserModelUserIdAndLeagueModelLeagueId(userId, leagueId);
    }

    public Integer getTotalNumberOfLeagueUserRoleInLeague(Integer leagueId) {
        return leagueUserRoleRepository.countLeagueUserRoleByLeagueId(leagueId);
    }

    public boolean doesLeagueUserRoleExistInLeague(Integer userId, Integer leagueId) {
        return leagueUserRoleRepository.existsByUserModelUserIdAndLeagueModelLeagueId(userId, leagueId);
    }

    public List<LeagueUserRoleModel> findLeagueUserRoleByUserId(Integer userId) {
        return leagueUserRoleRepository.findByUserModelUserId(userId);
    }

    public List<LeagueUserRoleModel> findLeagueUserRoleInLeagueByRole(Integer leagueId, LeagueRoleCode role) {
        return leagueUserRoleRepository.findByLeagueModelLeagueIdAndLeagueRole(leagueId, role);
    }

    public List<LeagueUserRoleModel> findLeagueUserRoleByLeagueId(Integer leagueId) {
        return leagueUserRoleRepository.findByLeagueModelLeagueId(leagueId);
    }
}
