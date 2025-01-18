package com.kleintwins.ftr.notification.util;

import com.kleintwins.ftr.notification.dto.CreateNotificationResponse;
import com.kleintwins.ftr.notification.dto.GetNotificationsResponse;
import com.kleintwins.ftr.notification.dto.Notification;
import com.kleintwins.ftr.notification.model.NotificationModel;

import java.util.List;

public class NotificationDtoBuilder {

    public static GetNotificationsResponse buildGetNotificationsResponse(List<NotificationModel> notificationModels) {
        GetNotificationsResponse getNotificationsResponse = new GetNotificationsResponse();
        for (NotificationModel notificationModel : notificationModels) {
            Notification notification = NotificationDtoBuilder.toNotification(notificationModel);
            getNotificationsResponse.getNotifications().add(notification);
        }
        return getNotificationsResponse;
    }

    public static CreateNotificationResponse buildCreateNotificationResponse(NotificationModel notificationModel) {
        return CreateNotificationResponse.builder()
                .notification(toNotification(notificationModel))
                .build();
    }

    public static Notification toNotification(NotificationModel notificationModel) {
        return Notification.builder()
                .notificationId(notificationModel.getNotificationId())
                .acknowledged(notificationModel.isAcknowledged())
                .payload(notificationModel.getPayload())
                .createTime(notificationModel.getCreateTime())
                .updateTime(notificationModel.getUpdateTime())
                .build();
    }
}
