package com.kleintwins.ftr.notification.dto;

import com.kleintwins.ftr.notification.model.NotificationPayload;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationRequest {
    private String userId;
    private NotificationPayload payload;
}
