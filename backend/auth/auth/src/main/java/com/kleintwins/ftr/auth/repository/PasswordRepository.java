package com.kleintwins.ftr.auth.repository;

import com.kleintwins.ftr.auth.model.PasswordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordModel, String> {
    Optional<PasswordModel> findByUserModelUserIdAndActive(String userId, Boolean active);
}