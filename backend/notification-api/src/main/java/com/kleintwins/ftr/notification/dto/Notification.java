package com.kleintwins.ftr.notification.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kleintwins.ftr.notification.model.NotificationPayload;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Notification {
    private String notificationId;
    private boolean acknowledged;
    private NotificationPayload payload;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime updateTime;
}
