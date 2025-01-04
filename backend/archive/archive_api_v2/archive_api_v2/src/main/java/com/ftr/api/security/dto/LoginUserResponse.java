package com.ftr.api.security.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LoginUserResponse {
    private final String accessToken;
    private final String tokenType = "Bearer ";

    public LoginUserResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
