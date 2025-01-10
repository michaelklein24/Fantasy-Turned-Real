package com.kleintwins.ftr.league.model;

import com.kleintwins.ftr.league.code.InviteStatus;
import com.kleintwins.ftr.user.model.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "lge_invite")
@Entity
@Getter
@Setter
@ToString(exclude = "league")
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InviteModel implements Serializable {

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

    @MapsId("invitee_user_id")
    @ManyToOne
    @JoinColumn(name = "invitee_user_id", referencedColumnName = "userId")
    private UserModel invitee;

    @ManyToOne
    @JoinColumn(name = "inviter_user_id", referencedColumnName = "userId")
    private UserModel inviter;

    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = LocalDateTime.now();
    }

    public InviteModel() { }
}
