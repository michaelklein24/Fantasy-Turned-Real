package com.kleintwins.ftr.league.dto;

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
    private String firstName;
    private String lastName;
    private LocalDateTime timestamp;
    private String email;
    private String leagueId;
}
