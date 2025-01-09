package com.kleintwins.ftr.league.code;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum InviteStatus {
    PENDING,
    APPROVED,
    DECLINED
}
