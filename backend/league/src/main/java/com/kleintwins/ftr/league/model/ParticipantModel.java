package com.kleintwins.ftr.league.model;

import com.kleintwins.ftr.league.code.LeagueRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "lge_participant")
@Entity
@Getter
@Setter
@ToString(exclude = "invites")
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantModel {

    @EmbeddedId
    private ParticipantId participantId;

    @Enumerated(value = EnumType.STRING)
    private LeagueRole role;

    private LocalDateTime timeJoined;

    @MapsId("leagueId")
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private LeagueModel league;

    @OneToOne(mappedBy = "invitee")
    private InviteModel invitee;

    @OneToOne(mappedBy = "inviter")
    private InviteModel inviter;

    public ParticipantModel() {}

    public ParticipantModel(ParticipantId participantId, LeagueRole role, LeagueModel league) {
        this.participantId = participantId;
        this.role = role;
        this.league = league;
    }

    @PrePersist
    protected void onCreate() {
        this.timeJoined = LocalDateTime.now();
    }
}
