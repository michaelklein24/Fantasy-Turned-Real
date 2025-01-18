package com.kleintwins.ftr.notification.service;

import com.kleintwins.ftr.auth.exception.UserNotPermitted;
import com.kleintwins.ftr.core.service.I18nService;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.notification.model.NotificationPayload;
import com.kleintwins.ftr.notification.repository.NotificationRepository;
import com.kleintwins.ftr.socket.service.SocketService;
import com.kleintwins.ftr.user.model.UserModel;
import com.kleintwins.ftr.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepo;
    private final UserService userService;
    private final SocketService socketService;
    private final I18nService i18nService;

    public List<NotificationModel> getNotificationsForUser(String userId) {
        UserModel userModel = userService.findUserByUserId(userId);
        return notificationRepo.findByUser(userModel);
    }

    public void notifyUserLeagueInvite(String userId, String leagueId) {
         NotificationPayload payload = NotificationPayload.builder()
                .title("League Invite")
                .message("You've been invited to join League " + leagueId)
                .icon("league_icon")
                .link("/league/" + leagueId)
                .timestamp(LocalDateTime.now())
                .build();

         NotificationModel notificationModel = createNotification(userId, payload);
    }

    private void sendNotificationToUser(@NonNull String userId, NotificationModel notificationModel) {
        socketService.sendNotificationToUser(userId, notificationModel);
    }

    public NotificationModel createNotification(String userId, NotificationPayload payload) {
        UserModel userModel = userService.findUserByUserId(userId);
        NotificationModel notificationModel =  notificationRepo.save(buildNotificationModel(userModel, payload));
        sendNotificationToUser(userId, notificationModel);
        return notificationModel;
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

    @Transactional
    public void markUserNotificationsAsAcknowledged(String userId, List<String> notificationIds, boolean acknowledged) {
        UserModel userModel = userService.findUserByUserId(userId);
        String currentUserId = userModel.getUserId();

        List<NotificationModel> notificationModels = notificationRepo.findAllById(notificationIds);

        List<String> errorMessages = new ArrayList<>();

        for (NotificationModel notificationModel : notificationModels) {
            if (!notificationModel.getUser().getUserId().equals(currentUserId)) {
                String message = i18nService.translate(
                        "api.notification.markNotificationsAsReadOrUnread.response.error.userNotAllowed.message",
                        notificationModel.getNotificationId(), currentUserId
                );
                errorMessages.add(message);
            } else {
                notificationModel.setAcknowledged(acknowledged);
            }
        }

        if (!errorMessages.isEmpty()) {
            throw new UserNotPermitted(String.join("\n", errorMessages));
        }

        notificationRepo.saveAll(notificationModels);
    }


}
