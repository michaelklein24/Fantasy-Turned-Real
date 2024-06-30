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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService extends AbstractService {
//
//    private final ParticipantRepository participantRepository;
//
//    public LeagueRoleCode getUserParticipantRoleForLeague(Integer userId, Integer leagueId) {
//        ParticipantModel participantModel = getParticipantByUserIdAndLeagueId(userId, leagueId).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find league role associated with userId %d and leagueId %d", userId, leagueId)));
//        return participantModel.getLeagueRole();
//    }
//
//    public Integer getTotalNumberOfParticipantsInLeague(Integer leagueId) {
//        return participantRepository.countParticipantsByLeagueId(leagueId);
//    }
//
//    public ParticipantModel createParticipant(UserModel userModel, LeagueModel leagueModel, LeagueRoleCode role) {
//        ParticipantModel participantModel = new ParticipantModel();
//        participantModel.setUserModel(userModel);
//        participantModel.setLeagueModel(leagueModel);
//        participantModel.setLeagueRole(role);
//        return participantRepository.save(participantModel);
//    }
//
//    public boolean doesParticipantWithUserIdExistInLeague(Integer userId, Integer leagueId) {
//        return participantRepository.existsByUserModelUserIdAndLeagueModelLeagueId(userId, leagueId);
//    }
//
//    public List<LeagueModel> getLeaguesForParticipant(Integer userId) {
//        List<ParticipantModel> participantModels = participantRepository.findByUserModelUserId(userId);
//        return participantModels.stream().map(ParticipantModel::getLeagueModel).toList();
//    }
//
//    public Optional<ParticipantModel> getAdminParticipantForLeague(Integer leagueId) {
//        return participantRepository.findByLeagueModelLeagueIdAndLeagueRole(leagueId, LeagueRoleCode.ADMIN).stream().findFirst();
//    }
//
//    public List<ParticipantModel> getParticipantsForLeague(Integer leagueId) {
//        return participantRepository.findByLeagueModelLeagueId(leagueId);
//    }
//
//    public Optional<ParticipantModel> getParticipantByUserIdAndLeagueId(Integer userId, Integer leagueId) {
//        return participantRepository.findByUserModelUserIdAndLeagueModelLeagueId(userId, leagueId);
//    }
}
