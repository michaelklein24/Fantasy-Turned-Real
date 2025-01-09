package com.kleintwins.ftr.league.dto;

import com.kleintwins.ftr.auth.dto.User;
import com.kleintwins.ftr.league.code.InviteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invite {
    private String leagueId;
    private User invitee;
    private User inviter;
    private InviteStatus status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
