package com.kleintwins.ftr.core.service;

import com.kleintwins.ftr.core.code.LeagueRole;
import com.kleintwins.ftr.core.model.LeagueModel;
import com.kleintwins.ftr.core.model.ParticipantId;
import com.kleintwins.ftr.core.model.ParticipantModel;
import com.kleintwins.ftr.core.repository.LeagueRepository;
import com.kleintwins.ftr.core.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepo;
    private final ParticipantRepository participantRepo;

    @Transactional
    public LeagueModel createLeague(String name, String ownerId) {
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setName(name);
        leagueModel = leagueRepo.save(leagueModel);

        ParticipantModel owner = new ParticipantModel();
        ParticipantId participantId = new ParticipantId(ownerId, leagueModel.getLeagueId());
        owner.setParticipantId(participantId);
        owner.setRole(LeagueRole.OWNER);
        owner.setLeague(leagueModel);
        participantRepo.save(owner);

        leagueModel.setParticipants(List.of(owner));
        return leagueModel;
    }
}
