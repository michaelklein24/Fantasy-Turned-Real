package com.kleintwins.ftr.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginUserResponse {
    @JsonProperty
    private final String accessToken;
    @JsonProperty
    private final String tokenType = "Bearer ";

    public LoginUserResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
