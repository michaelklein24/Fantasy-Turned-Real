package com.kleintwins.ftr.notification.service;

import com.kleintwins.ftr.notification.code.NotificationActionHttpMethod;
import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.model.NotificationAction;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.user.model.UserModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationModelBuilder {

    private String title;
    private String message;
    private String icon;
    private UserModel user;
    private NotificationReferenceType referenceType;
    private String referenceId;
    private List<NotificationAction> actions = new ArrayList<>();
    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();
    private boolean acknowledged = false;

    public NotificationModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public NotificationModelBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationModelBuilder withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public NotificationModelBuilder forUser(UserModel user) {
        this.user = user;
        return this;
    }

    public NotificationModelBuilder withReference(NotificationReferenceType referenceType, String referenceId) {
        this.referenceType = referenceType;
        this.referenceId = referenceId;
        return this;
    }

    public NotificationModelBuilder addAction(String label, String endpoint, String requestBody, NotificationActionHttpMethod method) {
        NotificationAction notificationAction = new NotificationAction();
        notificationAction.setLabel(label);
        notificationAction.setEndpoint(endpoint);
        notificationAction.setRequestBody(requestBody);
        notificationAction.setHttpMethod(method);
        this.actions.add(notificationAction);
        return this;
    }

    public NotificationModelBuilder acknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
        return this;
    }

    public NotificationModel build() {
        NotificationModel notification = new NotificationModel();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setIcon(icon);
        notification.setUser(user);
        notification.setReferenceId(referenceId);
        notification.setReferenceType(referenceType);
        notification.setActions(actions);
        notification.setCreateTime(createTime);
        notification.setUpdateTime(updateTime);
        notification.setAcknowledged(acknowledged);
        return notification;
    }
}
