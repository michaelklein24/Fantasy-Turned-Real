package com.ftr.api.league.dto;

import lombok.Data;

@Data
public class ResolveInviteRequest {
    private Integer inviteId;
    private boolean accepted;
}
