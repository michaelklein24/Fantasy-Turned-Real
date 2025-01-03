package com.kleintwins.ftr.auth.service;

import com.kleintwins.ftr.auth.exception.AccountAlreadyExists;
import com.kleintwins.ftr.auth.model.UserModel;
import com.kleintwins.ftr.auth.repository.UserRepository;
import com.kleintwins.ftr.core.service.AbstractService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService extends AbstractService {

    private final UserRepository userRepo;
    private final PasswordService passwordService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserModel registerUser(String firstName, String lastName, String email, String plainTextPassword) {
        if (userRepo.existsByEmail(email)) {
            String message = i18nService.translate("api.auth.register.response.error.emailAlreadyExists.message", email);
            throw new AccountAlreadyExists(message);
        }

        UserModel userModel = new UserModel(firstName, lastName, email);
        userRepo.save(userModel);

        passwordService.validatePassword(plainTextPassword);
        passwordService.createPasswordForUser(plainTextPassword, userModel);

        Authentication authentication = authenticateUser(userModel.getUserId(), plainTextPassword);

        return userModel;
    }

    private Authentication authenticateUser(String username, String plainTextPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, plainTextPassword));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }



}
