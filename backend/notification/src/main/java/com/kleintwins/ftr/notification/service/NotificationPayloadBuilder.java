package com.kleintwins.ftr.notification.service;

import com.kleintwins.ftr.notification.code.NotificationActionType;
import com.kleintwins.ftr.notification.code.NotificationType;
import com.kleintwins.ftr.notification.model.NotificationAction;
import com.kleintwins.ftr.notification.model.NotificationPayload;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationPayloadBuilder {

    private String title;
    private String message;
    private String icon;
    private NotificationType type;
    private String link;
    private LocalDateTime timestamp;
    private final List<NotificationAction> actions = new ArrayList<>();

    public NotificationPayloadBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public NotificationPayloadBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationPayloadBuilder withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public NotificationPayloadBuilder withType(NotificationType type) {
        this.type = type;
        return this;
    }

    public NotificationPayloadBuilder withLink(String link) {
        this.link = link;
        return this;
    }

    public NotificationPayloadBuilder withTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public NotificationPayloadBuilder addAction(String label, NotificationActionType actionType, String endpoint) {
        NotificationAction action = new NotificationAction();
        action.setLabel(label);
        action.setActionType(actionType);
        action.setEndpoint(endpoint);
        this.actions.add(action);
        return this;
    }

    // Convenience method for league invite notifications
    public NotificationPayloadBuilder forLeagueInvite(String leagueId, String leagueName, String inviterName) {
        this.title = "League Invitation";
        this.message = inviterName + " has invited you to join the league: " + leagueName;
        this.icon = "league_icon.png";
        this.type = NotificationType.INFO;
        this.timestamp = LocalDateTime.now();
        this.actions.clear();
        String acceptEndpoint = "/api/leagues/" + leagueId + "/invite?status=accept";
        String declineEndpoint = "/api/leagues/" + leagueId + "/invite?status=decline";
        this.addAction("Accept", NotificationActionType.ACCEPT, acceptEndpoint);
        this.addAction("Decline", NotificationActionType.DECLINE, declineEndpoint);
        return this;
    }

    // Build method to create the NotificationPayload
    public NotificationPayload build() {
        NotificationPayload payload = new NotificationPayload();
        payload.setTitle(this.title);
        payload.setMessage(this.message);
        payload.setIcon(this.icon);
        payload.setType(this.type);
        payload.setLink(this.link);
        payload.setTimestamp(this.timestamp);
        payload.setActions(this.actions);
        return payload;
    }
}
