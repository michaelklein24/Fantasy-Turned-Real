package com.ftr.api.security.service;

import com.ftr.api.core.service.AbstractService;
import com.ftr.api.security.dto.*;
import com.ftr.api.user.code.CheckUsernameResponseTypeCode;
import com.ftr.api.user.model.UserModel;
import com.ftr.api.user.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService extends AbstractService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        String token = this.registerUser(
                registerUserRequest.getFirstName(),
                registerUserRequest.getLastName(),
                registerUserRequest.getUsername(),
                registerUserRequest.getEmail(),
                registerUserRequest.getPassword());
        return new RegisterUserResponse(token);
    }

    @Transactional
    public String registerUser(String firstName, String lastName, String username, String email, String plainTextPassword) {
        if (userService.doesEmailAlreadyExist(email)) {
            throw new EntityExistsException(String.format("Email '%s' already exists", email));
        }
        if (userService.doesUsernameAlreadyExist(username)) {
            throw new EntityExistsException(String.format("Username '%s' already exists", username));
        }

        String encodedPassword = encodePassword(plainTextPassword);
        UserModel userModel = userService.saveNewUser(firstName, lastName, username, email, encodedPassword);

        Authentication authentication = authenticateUser(username, plainTextPassword);

        return generateToken(userModel);
    }

    public LoginUserResponse loginUser(LoginUserRequest request) {
        UserModel userModel = this.findUserByUsername(request.getUsername()).orElseThrow(() -> new BadCredentialsException("Bad Credentials"));

        Authentication authentication = authenticateUser(request.getUsername(), request.getPassword());

        String token = generateToken(userModel);
        return new LoginUserResponse(token);
    }

    public CheckUsernameResponseTypeCode checkEmail(CheckUsernameRequest request) {
        if (userService.doesEmailAlreadyExist(request.getUsername())) {
            return CheckUsernameResponseTypeCode.USERNAME_ALREADY_EXISTS;
        }
        return CheckUsernameResponseTypeCode.VALID;
    }

    protected String encodePassword(String plainTextPassword) {
        return passwordEncoder.encode(plainTextPassword);
    }

    private String generateToken(UserModel userModel) {
        return jwtGenerator.generateToken(userModel);
    }

    private Authentication authenticateUser(String username, String plainTextPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, plainTextPassword));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public Optional<UserModel> findUserByUsername(String username) {
        return userService.findUserByUsername(username);
    }

    public CheckUsernameResponse checkUsername(CheckUsernameRequest request) {
        CheckUsernameResponse response = new CheckUsernameResponse();
        boolean doesEmailAlreadyExist = userService.doesEmailAlreadyExist(request.getUsername());

        if (doesEmailAlreadyExist) {
            response.setCheckUsernameResponseTypeCode(CheckUsernameResponseTypeCode.USERNAME_ALREADY_EXISTS);
        } else {
            response.setCheckUsernameResponseTypeCode(CheckUsernameResponseTypeCode.VALID);
        }

        return response;
    }


}
