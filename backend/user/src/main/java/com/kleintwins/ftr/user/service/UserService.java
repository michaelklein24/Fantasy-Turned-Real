package com.kleintwins.ftr.user.service;

import com.kleintwins.ftr.user.model.UserModel;
import com.kleintwins.ftr.user.repository.UserRepository;
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
