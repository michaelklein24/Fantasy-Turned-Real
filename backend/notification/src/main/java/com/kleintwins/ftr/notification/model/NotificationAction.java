package com.kleintwins.ftr.notification.model;

import com.kleintwins.ftr.notification.code.NotificationActionHttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationAction implements Serializable {
    private String label;
    private NotificationActionHttpMethod httpMethod;
    private String endpoint;
    private Object requestBody;
}
