package com.kleintwins.ftr.notification.dto;

import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationRequest {
    private NotificationReferenceType referenceType;
    private String referenceId;
    private String userId;
    private String[] args;
}
