package com.kleintwins.ftr.notification.service;

import com.kleintwins.ftr.notification.code.NotificationType;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.notification.model.NotificationPayload;
import com.kleintwins.ftr.notification.repository.NotificationRepository;
import com.kleintwins.ftr.user.model.UserModel;
import com.kleintwins.ftr.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepo;
    private final UserService userService;

    public List<NotificationModel> getNotificationsForUser(String userId) {
        UserModel userModel = userService.findUserByUserId(userId);
        return notificationRepo.findByUser(userModel);
    }

    public void notifyUserLeagueInvite(UserModel userModel, String leagueId) {
         NotificationPayload payload = NotificationPayload.builder()
                .title("League Invite")
                .message("You've been invited to join League " + leagueId)
                .icon("league_icon")
                .link("/league/" + leagueId)
                .timestamp(LocalDateTime.now())
                .build();

         NotificationModel notificationModel = buildNotificationModel(userModel, payload);
         notificationRepo.save(notificationModel);
    }

    private NotificationModel buildNotificationModel(UserModel user, NotificationPayload payload) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setUser(user);
        notificationModel.setPayload(payload);
        notificationModel.setCreateTime(LocalDateTime.now());
        notificationModel.setUpdateTime(LocalDateTime.now());
        notificationModel.setAcknowledged(false);
        return notificationModel;
    }

    public NotificationModel saveNotification(NotificationModel notificationModel) {
        return notificationRepo.save(notificationModel);
    }
}
