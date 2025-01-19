package com.kleintwins.ftr.notification.code;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Notification Type", enumAsRef = true)
public enum NotificationReferenceType {
    LEAGUE_INVITE
}
