package com.kleintwins.ftr.league.model;

import com.kleintwins.ftr.league.code.LeagueRole;
import com.kleintwins.ftr.user.model.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "lge_participant")
@Entity
@Getter
@Setter
@ToString()
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipantModel implements Serializable {

    @EmbeddedId
    private ParticipantId participantId;

    @Enumerated(value = EnumType.STRING)
    private LeagueRole role;

    private LocalDateTime timeJoined;

    @MapsId("leagueId")
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private LeagueModel league;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", insertable = false, updatable = false)
    private UserModel user;

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
