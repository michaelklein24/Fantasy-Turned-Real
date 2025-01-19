package com.kleintwins.ftr.notification.util;

import com.kleintwins.ftr.notification.code.NotificationActionHttpMethod;
import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.notification.service.NotificationModelBuilder;
import com.kleintwins.ftr.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class LeagueInviteNotificationBuilder implements INotificationBuilder {

    @Override
    public NotificationReferenceType getSupportedType() {
        return NotificationReferenceType.LEAGUE_INVITE;
    }

    @Override
    public NotificationModel build(UserModel user, String referenceId, String... args) {
        // args[0] = inviterName, args[1] = leagueName
        return new NotificationModelBuilder()
                .withTitle("League Invitation")
                .withMessage(args[0] + " has invited you to join the league: " + args[1])
                .withIcon("group")
                .forUser(user)
                .withReference(NotificationReferenceType.LEAGUE_INVITE, referenceId)
                .addAction("Accept", "/league/" + referenceId + "/invite", "{\"newStatus\": \"APPROVED\"}", NotificationActionHttpMethod.PUT)
                .addAction("Decline", "/league/" + referenceId + "/invite", "{\"newStatus\": \"DECLINED\"}", NotificationActionHttpMethod.PUT)
                .build();
    }
}
