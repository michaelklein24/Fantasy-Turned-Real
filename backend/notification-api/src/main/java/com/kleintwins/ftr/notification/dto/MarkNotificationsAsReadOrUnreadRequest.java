package com.kleintwins.ftr.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarkNotificationsAsReadOrUnreadRequest {
    private List<String> notificationIds;
    private boolean isAcknowledged;
}
