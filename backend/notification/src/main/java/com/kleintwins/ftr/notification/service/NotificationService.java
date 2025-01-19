package com.kleintwins.ftr.notification.service;

import com.kleintwins.ftr.auth.exception.UserNotPermitted;
import com.kleintwins.ftr.core.SortOrder;
import com.kleintwins.ftr.core.exception.EntityNotFound;
import com.kleintwins.ftr.core.service.I18nService;
import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.notification.repository.NotificationRepository;
import com.kleintwins.ftr.notification.util.INotificationBuilder;
import com.kleintwins.ftr.socket.service.SocketService;
import com.kleintwins.ftr.user.model.UserModel;
import com.kleintwins.ftr.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepo;
    private final UserService userService;
    private final SocketService socketService;
    private final I18nService i18nService;
    private final List<INotificationBuilder> notificationBuilders;

    public List<NotificationModel> getNotificationsForUser(String userId) {
        UserModel userModel = userService.findUserByUserId(userId);
        return notificationRepo.findByUser(userModel);
    }

    public List<NotificationModel> getNotificationsForUser(String userId, SortOrder order, int fetchSize, List<NotificationReferenceType> notificationTypes) {
        UserModel userModel = userService.findUserByUserId(userId);
        Sort sortOrder = Sort.by("createTime");
        if (SortOrder.DESC.equals(order)) {
            sortOrder = sortOrder.descending();
        } else {
            sortOrder = sortOrder.ascending();
        }
        Pageable pageable = PageRequest.of(0, fetchSize, sortOrder);
        return notificationRepo.findNotifications(userModel, notificationTypes, pageable).getContent();

    }

    private void sendNotificationToUser(@NonNull String userId, NotificationModel notificationModel) {
        socketService.sendNotificationToUser(userId, notificationModel);
    }

    public NotificationModel createNotification(String userId, String referenceId, NotificationReferenceType referenceType, String[] args) {
        UserModel userModel = userService.findUserByUserId(userId);

        INotificationBuilder notificationBuilder = notificationBuilders.stream()
                .filter(b -> b.getSupportedType().equals(referenceType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No builder found for notification type: " + referenceType));

        NotificationModel notificationModel  = notificationRepo.save(notificationBuilder.build(userModel, referenceId, args));
        sendNotificationToUser(userId, notificationModel);
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

    public NotificationModel findLeagueInviteNotification(String leagueId) {
        return notificationRepo.findByReferenceTypeAndReferenceId(NotificationReferenceType.LEAGUE_INVITE, leagueId).orElseThrow(
                () -> {
                    String message = i18nService.translate(
                            "api.notification.findByLeagueInviteAndLeagueId.response.error.notFound.message",
                            NotificationReferenceType.LEAGUE_INVITE.name(), leagueId
                    );
                    return new EntityNotFound(message);
                });
    }

    public void updateNotificationCompletedAction(NotificationModel notificationModel, String actionLabel) {
        // Ensure the action label is valid (optional: could add validation for the actionLabel here)
        if (actionLabel != null && !actionLabel.isEmpty()) {
            // Update the completedActionLabel field
            notificationModel.setCompletedActionLabel(actionLabel);
            notificationRepo.save(notificationModel);
        } else {
            throw new IllegalArgumentException("Action label cannot be null or empty.");
        }
    }

}
