package com.kleintwins.ftr.auth.controller;

import com.kleintwins.ftr.auth.dto.LoginUserRequest;
import com.kleintwins.ftr.auth.dto.LoginUserResponse;
import com.kleintwins.ftr.auth.dto.RegisterUserRequest;
import com.kleintwins.ftr.auth.dto.RegisterUserResponse;
import com.kleintwins.ftr.auth.service.AuthService;
import com.kleintwins.ftr.auth.service.TokenService;
import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.user.model.UserModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Register a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterUserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Unable to register user due to bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Email already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<RegisterUserResponse> register(@RequestBody @Valid RegisterUserRequest request) {
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String email = request.getEmail();
        String plainTextPassword = request.getPassword();

        UserModel registeredUser = authService.registerUser(firstName, lastName, email, plainTextPassword);
        String token = tokenService.generateToken(registeredUser);
        RegisterUserResponse response = new RegisterUserResponse(token);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged user in",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginUserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Unable to login user due to bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<LoginUserResponse> login(@RequestBody @Valid LoginUserRequest request) {
        String email = request.getEmail();
        String plainTextPassword = request.getPassword();

        UserModel loggedInUser = authService.loginUser(email, plainTextPassword);
        String token = tokenService.generateToken(loggedInUser);
        LoginUserResponse response = new LoginUserResponse(token);

        return ResponseEntity.ok().body(response);
    }
}
