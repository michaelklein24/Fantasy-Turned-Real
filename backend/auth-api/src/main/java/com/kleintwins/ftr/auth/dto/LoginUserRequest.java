package com.kleintwins.ftr.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserRequest {
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "password must not be blank")
    private String password;

    public LoginUserRequest() {}

    public LoginUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
