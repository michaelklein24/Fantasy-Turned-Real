package com.ftr.api.league.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.league.code.LeagueRoleCode;
import com.ftr.api.league.model.LeagueModel;
import com.ftr.api.league.model.ParticipantModel;
import com.ftr.api.league.repository.ParticipantRepository;
import com.ftr.api.user.model.UserModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService extends AbstractService {

    private final ParticipantRepository participantRepository;

    public LeagueRoleCode getUserParticipantRoleForLeague(Integer userId, Integer leagueId) {
        ParticipantModel participantModel = participantRepository.findByUserModelUserIdAndLeagueModelLeagueId(userId, leagueId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find league role associated with userId %d and leagueId %d", userId, leagueId)));
        return participantModel.getLeagueRole();
    }

    public Integer getTotalNumberOfParticipantsInLeague(Integer leagueId) {
        return participantRepository.countParticipantsByLeagueId(leagueId);
    }

    public ParticipantModel createParticipant(UserModel userModel, LeagueModel leagueModel, LeagueRoleCode role) {
        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setUserModel(userModel);
        participantModel.setLeagueModel(leagueModel);
        participantModel.setLeagueRole(role);
        return participantRepository.save(participantModel);
    }

    public boolean doesParticipantWithUserIdExistInLeague(Integer userId, Integer leagueId) {
        return participantRepository.existsByUserModelUserIdAndLeagueModelLeagueId(userId, leagueId);
    }

    public List<LeagueModel> getLeaguesForParticipant(Integer userId) {
        List<ParticipantModel> participantModels = participantRepository.findByUserModelUserId(userId);
        return participantModels.stream().map(ParticipantModel::getLeagueModel).toList();
    }
}
