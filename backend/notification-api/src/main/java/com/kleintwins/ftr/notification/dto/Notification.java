package com.kleintwins.ftr.notification.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.model.NotificationAction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Notification {
    private String notificationId;
    private boolean acknowledged;
    private String title;
    private String message;
    private String icon;
    private String link;
    private List<NotificationAction> actions;
    private String completedActionLabel;
    private NotificationReferenceType referenceType;
    private String referenceId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime updateTime;
}
