package com.kleintwins.ftr.league.service;

import com.kleintwins.ftr.league.code.LeagueRole;
import com.kleintwins.ftr.league.model.InviteModel;
import com.kleintwins.ftr.league.model.LeagueModel;
import com.kleintwins.ftr.league.model.ParticipantId;
import com.kleintwins.ftr.league.model.ParticipantModel;
import com.kleintwins.ftr.league.repository.LeagueRepository;
import com.kleintwins.ftr.league.repository.ParticipantRepository;
import com.kleintwins.ftr.show.code.Show;
import com.kleintwins.ftr.show.model.SeasonModel;
import com.kleintwins.ftr.show.service.SeasonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepo;
    private final ParticipantRepository participantRepo;

    private final SeasonService seasonService;

    @Transactional
    public LeagueModel createLeague(String name, String ownerId, Show show, int seasonSequence) {
        SeasonModel seasonModel = seasonService.findByShowAndSeasonId(show, seasonSequence);

        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setName(name);
        leagueModel.setSeason(seasonModel);
        LeagueModel savedLeague = leagueRepo.save(leagueModel);

        ParticipantModel owner = new ParticipantModel();
        ParticipantId participantId = new ParticipantId(ownerId, savedLeague.getLeagueId());
        owner.setParticipantId(participantId);
        owner.setRole(LeagueRole.OWNER);
        owner.setLeague(savedLeague);
        participantRepo.save(owner);

        leagueModel.setParticipants(List.of(owner));
        return leagueModel;
    }

    public List<LeagueModel> getLeaguesForUser(String userId) {
        List<ParticipantModel> participantModels = participantRepo.findByParticipantIdUserId(userId);
        return participantModels.stream().map(ParticipantModel::getLeague).toList();
    }

    public InviteModel createInvite(String inviteeEmail, String inviterEmail, String leagueId) {

        ParticipantId inviterParticipantId = new ParticipantId(inviterEmail, leagueId);

//        Optional<ParticipantModel> inviter = participantRepo.findById();
//        Optional<ParticipantModel> invitee = participantRepo.findById();

        return InviteModel.builder().build();
    }
}
