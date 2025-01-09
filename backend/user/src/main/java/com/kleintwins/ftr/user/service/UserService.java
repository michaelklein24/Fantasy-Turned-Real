package com.kleintwins.ftr.user.service;

import com.kleintwins.ftr.core.exception.EntityNotFound;
import com.kleintwins.ftr.core.service.I18nService;
import com.kleintwins.ftr.user.model.UserModel;
import com.kleintwins.ftr.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final I18nService i18nService;

    public UserModel findUserByUserId(String userId) {
        return userRepo.findById(userId).orElseThrow(
            () -> {
                String message = i18nService.translate(
                        "api.user.findUserByUser.response.error.notFound.message",
                        userId
                );
                return new EntityNotFound(message);
            });
    }

    public UserModel findUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(
            () -> {
                String message = i18nService.translate(
                        "api.user.findUserByEmail.response.error.notFound.message",
                        email
                );
                return new EntityNotFound(message);
            });
    }
}
