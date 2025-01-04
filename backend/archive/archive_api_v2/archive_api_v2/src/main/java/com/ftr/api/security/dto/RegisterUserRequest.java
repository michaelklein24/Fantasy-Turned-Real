package com.ftr.api.security.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
