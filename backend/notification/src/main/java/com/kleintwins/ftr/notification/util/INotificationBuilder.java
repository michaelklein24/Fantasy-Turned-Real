package com.kleintwins.ftr.notification.util;

import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.user.model.UserModel;

public interface INotificationBuilder {
    NotificationReferenceType getSupportedType();
    NotificationModel build(UserModel user, String referenceId, String... args);
}
