package com.kleintwins.ftr.core.model;

import com.kleintwins.ftr.core.code.LeagueRole;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "lge_participant")
@Entity
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantModel {
    @EmbeddedId
    private ParticipantId participantId;
    @Enumerated(value = EnumType.STRING)
    private LeagueRole role;
    @MapsId("leagueId")
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private LeagueModel league;

    public ParticipantModel() {}

    public ParticipantModel(ParticipantId participantId, LeagueRole role, LeagueModel league) {
        this.participantId = participantId;
        this.role = role;
        this.league = league;
    }
}
