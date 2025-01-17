package com.kleintwins.ftr.notification.dto;

import com.kleintwins.ftr.notification.code.NotificationType;
import com.kleintwins.ftr.notification.model.NotificationPayload;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Notification {
    private String notificationId;
    private boolean acknowledged;
    private NotificationPayload payload;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
