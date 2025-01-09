package com.kleintwins.ftr.league.dto;

import com.kleintwins.ftr.league.code.InviteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInviteStatusRequest {
    private InviteStatus newStatus;
}
