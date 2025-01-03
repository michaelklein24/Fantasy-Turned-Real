package com.kleintwins.ftr.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserResponse {
    @JsonProperty
    private final String accessToken;
    @JsonProperty
    private final String tokenType = "Bearer ";

    public RegisterUserResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
