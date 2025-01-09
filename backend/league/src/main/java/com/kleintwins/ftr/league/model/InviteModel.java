package com.kleintwins.ftr.league.model;

import com.kleintwins.ftr.league.code.InviteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "lge_invite")
@Entity
@Getter
@Setter
@ToString(exclude = "league")
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InviteModel {

    @EmbeddedId
    private InviteId inviteId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Enumerated(value = EnumType.STRING)
    private InviteStatus status;

    @MapsId("leagueId")
    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "leagueId", nullable = false)
    private LeagueModel league;

    @OneToOne
    @PrimaryKeyJoinColumns({
            @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "userId"),
            @PrimaryKeyJoinColumn(name = "league_id", referencedColumnName = "leagueId")
    })
    private ParticipantModel inviter;

    @OneToOne
    @PrimaryKeyJoinColumns({
            @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "userId"),
            @PrimaryKeyJoinColumn(name = "league_id", referencedColumnName = "leagueId")
    })
    private ParticipantModel invitee;

    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}
