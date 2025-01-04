package com.ftr.api.league.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetPendingInvitesForUserResponse {
    private List<InviteDetails> invites;
}
