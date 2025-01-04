package com.kleintwins.ftr.auth.service;

import com.kleintwins.ftr.auth.model.UserModel;
import com.kleintwins.ftr.auth.repository.PasswordRepository;
import com.kleintwins.ftr.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public Optional<UserModel> findUserByUserId(String username) {
        return userRepo.findById(username);
    }
}
