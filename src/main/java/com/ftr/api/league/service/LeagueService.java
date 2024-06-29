package com.ftr.api.league.service;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeagueService implements IDaoImpl<LeagueModel> {

    private final LeagueRepository leagueRepository;
    private final ParticipantService participantService;

    public List<LeagueModel> getLeaguesForUser(Integer userId) {
        return participantService.getLeaguesForParticipant(userId);
    }

    public void deleteLeague(Integer leagueId, Integer userId) {
        leagueRepository.deleteById(leagueId);
    }


    @Override
    public LeagueModel saveEntity(LeagueModel entity) {
        return leagueRepository.save(entity);
    }

    @Override
    public Optional<LeagueModel> findEntityById(Integer id) {
        return leagueRepository.findById(id);
    }

    @Override
    public void updateEntity(LeagueModel entity) {
        leagueRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        leagueRepository.deleteById(id);
    }
}
