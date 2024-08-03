package com.ftr.api.security.controller;

import com.ftr.api.core.controller.AbstractController;
import com.ftr.api.security.dto.*;
import com.ftr.api.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController extends AbstractController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
        RegisterUserResponse response = authService.registerUser(request);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        LoginUserResponse response = authService.loginUser(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/checkUsername")
    public ResponseEntity<CheckUsernameResponse> checkUsername(@RequestBody CheckUsernameRequest request) {
        CheckUsernameResponse response = new CheckUsernameResponse();
        response.setCheckUsernameResponseTypeCode(authService.checkUsername(request));
        return ResponseEntity.ok().body(response);
    }

}
