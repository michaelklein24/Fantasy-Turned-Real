package com.kleintwins.ftr.core.service;

import com.kleintwins.ftr.core.code.LeagueRole;
import com.kleintwins.ftr.core.model.LeagueModel;
import com.kleintwins.ftr.core.model.ParticipantId;
import com.kleintwins.ftr.core.model.ParticipantModel;
import com.kleintwins.ftr.core.repository.LeagueRepository;
import com.kleintwins.ftr.core.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeagueServiceTest {

    @Mock
    private LeagueRepository leagueRepo;

    @Mock
    private ParticipantRepository participantRepo;

    @InjectMocks
    private LeagueService leagueService;

    private final String leagueName = "Super League";
    private final String ownerId = UUID.randomUUID().toString();
    private final String leagueId = UUID.randomUUID().toString();

    @Test
    void createLeague_success() {
        // Arrange
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.setLeagueId(leagueId);
        leagueModel.setName(leagueName);

        ParticipantModel ownerParticipant = new ParticipantModel();
        ownerParticipant.setParticipantId(new ParticipantId(ownerId, leagueId));
        ownerParticipant.setRole(LeagueRole.OWNER);
        ownerParticipant.setLeague(leagueModel);

        // Mocking repository behavior
        when(leagueRepo.save(any(LeagueModel.class))).thenReturn(leagueModel);
        when(participantRepo.save(any(ParticipantModel.class))).thenReturn(ownerParticipant);

        // Act
        LeagueModel createdLeague = leagueService.createLeague(leagueName, ownerId);

        // Assert
        assertNotNull(createdLeague);
        assertEquals(leagueId, createdLeague.getLeagueId());
        assertEquals(leagueName, createdLeague.getName());
        assertEquals(1, createdLeague.getParticipants().size());

        ParticipantModel participant = createdLeague.getParticipants().get(0);
        assertEquals(ownerId, participant.getParticipantId().getUserId());
        assertEquals(LeagueRole.OWNER, participant.getRole());

        // Verify interactions
        verify(leagueRepo, times(1)).save(any(LeagueModel.class));
        verify(participantRepo, times(1)).save(any(ParticipantModel.class));
    }
}
