package com.kleintwins.ftr.notification.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.user.model.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private String title;
    private String message;
    private String icon;
    private String link;
    @Column(columnDefinition = "TEXT")
    @Convert(converter = NotificationActionListConverter.class)
    private List<NotificationAction> actions;
    private String completedActionLabel;
    @Enumerated(value = EnumType.STRING)
    private NotificationReferenceType referenceType;
    private String referenceId;
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
