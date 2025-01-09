package com.kleintwins.ftr.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterUserRequest implements Serializable {
    @NotBlank(message = "firstName must not be blank")
    private String firstName;
    @NotBlank(message = "lastName must not be blank")
    private String lastName;
    @NotBlank(message = "email must not be blank")
    @Email
    private String email;
    @NotBlank(message = "password must not be blank")
    private String password;

    public RegisterUserRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public RegisterUserRequest() {}

    @Override
    public RegisterUserRequest clone() {
        try {
            return (RegisterUserRequest) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
