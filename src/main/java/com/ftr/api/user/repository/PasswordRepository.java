package com.ftr.api.user.repository;

import com.ftr.api.user.model.PasswordModel;
import com.ftr.api.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordModel, Integer> {
    Optional<PasswordModel> findByUserModelAndActive(UserModel userModel, Boolean active);
}
