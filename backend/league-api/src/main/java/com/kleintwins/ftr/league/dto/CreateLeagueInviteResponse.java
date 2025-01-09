package com.kleintwins.ftr.league.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLeagueInviteResponse {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime inviteTime;
}
