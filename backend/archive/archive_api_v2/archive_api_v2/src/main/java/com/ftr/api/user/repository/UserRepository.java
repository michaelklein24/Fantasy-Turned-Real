package com.ftr.api.user.repository;

import com.ftr.api.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
