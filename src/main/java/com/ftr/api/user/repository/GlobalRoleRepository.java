package com.ftr.api.user.repository;

import com.ftr.api.user.model.GlobalRoleModel;
import com.ftr.api.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GlobalRoleRepository extends JpaRepository<GlobalRoleModel, Integer> {
    Optional<GlobalRoleModel> findByUserModel(UserModel userModel);
}
