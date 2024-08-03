package com.ftr.api.league.dao;

import com.ftr.api.core.service.IDaoImpl;
import com.ftr.api.league.code.InviteStatusCode;
import com.ftr.api.league.model.InviteModel;
import com.ftr.api.league.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InviteDao implements IDaoImpl<InviteModel> {

    @Autowired
    private InviteRepository inviteRepository;

    @Override
    public InviteModel saveEntity(InviteModel entity) {
        return inviteRepository.save(entity);
    }

    @Override
    public Optional<InviteModel> findEntityById(Integer id) {
        return inviteRepository.findById(id);
    }

    @Override
    public void updateEntity(InviteModel entity) {
        inviteRepository.save(entity);
    }

    @Override
    public void deleteEntityById(Integer id) {
        inviteRepository.deleteById(id);
    }

    public List<InviteModel> findInviteByEmailAndStatus(String email, InviteStatusCode status) {
        return inviteRepository.findByEmailAndStatus(email, status);
    }

    public Optional<InviteModel> findInviteByEmailAndStatusAndLeagueId(String email, InviteStatusCode status, Integer leagueId) {
        return inviteRepository.findByEmailAndStatusAndLeagueModelLeagueId(email, status, leagueId);
    }

}
