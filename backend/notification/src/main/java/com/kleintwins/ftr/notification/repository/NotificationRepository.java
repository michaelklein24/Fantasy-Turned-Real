package com.kleintwins.ftr.notification.repository;

import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, String> {
    List<NotificationModel> findByUser(UserModel userModel);
    Optional<NotificationModel> findByReferenceTypeAndReferenceId(NotificationReferenceType type, String referenceId);
}
