package com.ftr.api.league.dao;

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
public class LeagueDao implements IDaoImpl<LeagueModel> {

    private final LeagueRepository leagueRepository;

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
