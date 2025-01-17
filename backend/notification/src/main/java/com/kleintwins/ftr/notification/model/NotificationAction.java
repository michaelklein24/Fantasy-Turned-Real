package com.kleintwins.ftr.notification.model;

import com.kleintwins.ftr.notification.code.NotificationActionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationAction implements Serializable {
    private String label;
    private NotificationActionType actionType;
    private String endpoint;
}
