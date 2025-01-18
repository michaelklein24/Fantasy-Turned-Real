package com.kleintwins.ftr.notification.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kleintwins.ftr.notification.code.NotificationType;
import com.kleintwins.ftr.user.model.UserModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Table(name = "ntf_notification")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String notificationId;

    private boolean acknowledged;

    @Convert(converter = NotificationPayloadJsonConverter.class)
    private NotificationPayload payload;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserModel user;

    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}
