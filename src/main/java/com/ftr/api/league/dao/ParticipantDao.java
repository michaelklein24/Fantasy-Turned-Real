package com.ftr.api.league.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.league.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantDao implements IDaoImpl<ParticipantModel> {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public ParticipantModel saveEntity(ParticipantModel entity) {
        return participantRepository.save(entity);
    }

    @Override
    public Optional<ParticipantModel> findEntityById(Integer id) {
        return participantRepository.findById(id);
    }

    @Override
    public void updateEntity(ParticipantModel entity) {
        participantRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        participantRepository.deleteById(id);
    }


    public Optional<ParticipantModel> findParticipantByUserIdAndLeagueId(Integer userId, Integer leagueId) {
        return participantRepository.findByUserModelUserIdAndLeagueModelLeagueId(userId, leagueId);
    }

    public Integer getTotalNumberOfParticipantsInLeague(Integer leagueId) {
        return participantRepository.countParticipantsByLeagueId(leagueId);
    }

    public boolean doesParticipantExistInLeague(Integer userId, Integer leagueId) {
        return participantRepository.existsByUserModelUserIdAndLeagueModelLeagueId(userId, leagueId);
    }

    // TODO: Fix this one.  I don't really like how this one is in the Participant Dao
    public List<ParticipantModel> findParticipantsByUserId(Integer userId) {
        return participantRepository.findByUserModelUserId(userId);
    }

    public List<ParticipantModel> findParticipantsInLeagueByRole(Integer leagueId, LeagueRoleCode role) {
        return participantRepository.findByLeagueModelLeagueIdAndLeagueRole(leagueId, role);
    }

    public List<ParticipantModel> findParticipantsByLeagueId(Integer leagueId) {
        return participantRepository.findByLeagueModelLeagueId(leagueId);
    }
}
