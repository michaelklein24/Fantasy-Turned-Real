package com.kleintwins.ftr.notification.model;

import com.kleintwins.ftr.notification.code.NotificationActionType;
import com.kleintwins.ftr.notification.code.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPayload implements Serializable {
    private String title;
    private String message;
    private String icon;
    private NotificationType type;
    private String link;
    private LocalDateTime timestamp;
    private List<NotificationAction> actions;
    private NotificationAction completedAction;
}
