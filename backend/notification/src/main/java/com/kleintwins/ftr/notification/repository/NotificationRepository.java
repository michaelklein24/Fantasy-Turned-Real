package com.kleintwins.ftr.notification.repository;

import com.kleintwins.ftr.core.SortOrder;
import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.user.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, String> {
    List<NotificationModel> findByUser(UserModel userModel);
    Optional<NotificationModel> findByReferenceTypeAndReferenceId(NotificationReferenceType type, String referenceId);

    @Query("SELECT n FROM NotificationModel n WHERE n.user = :user " +
            "AND (:notificationTypes IS NULL OR n.referenceType IN :notificationTypes)")
    Page<NotificationModel> findNotifications(
            @Param("user") UserModel userModel,
            @Param("notificationTypes") List<NotificationReferenceType> notificationTypes,
            Pageable pageable
    );

}
