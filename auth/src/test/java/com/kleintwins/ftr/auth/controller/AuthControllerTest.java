package com.kleintwins.ftr.auth.controller;

import com.kleintwins.ftr.auth.dto.RegisterUserRequest;
import com.kleintwins.ftr.auth.dto.RegisterUserResponse;
import com.kleintwins.ftr.auth.exception.AccountAlreadyExists;
import com.kleintwins.ftr.auth.exception.InvalidPassword;
import com.kleintwins.ftr.auth.model.UserModel;
import com.kleintwins.ftr.auth.service.AuthService;
import com.kleintwins.ftr.auth.service.TokenService;
import com.kleintwins.ftr.core.service.I18nService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private I18nService i18nService;

    private RegisterUserRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new RegisterUserRequest("John", "Doe", "john.doe@example.com", "ValidPassword123!");
    }

    @Test
    void shouldRegisterUserAndReturnToken() throws Exception {
        // Arrange
        String expectedToken = "mockToken";
        RegisterUserResponse response = new RegisterUserResponse(expectedToken);

        when(authService.registerUser(any(), any(), any(), any())).thenReturn(new UserModel());
        when(tokenService.generateToken(any())).thenReturn(expectedToken);

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"ValidPassword123!\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(expectedToken));

        // Verify interactions
        verify(authService).registerUser(any(), any(), any(), any());
        verify(tokenService).generateToken(any());
    }

    @Test
    void shouldReturnEmailAlreadyExistsError() throws Exception {
        // Arrange
        when(authService.registerUser(any(), any(), any(), any())).thenThrow(new AccountAlreadyExists("Account already exists"));

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"ValidPassword123!\"}"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("403"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Account already exists"));
    }

    @Test
    void shouldReturnInvalidPasswordError() throws Exception {
        // Arrange
        when(authService.registerUser(any(), any(), any(), any())).thenThrow(new InvalidPassword("Invalid password"));

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"short\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid password"));
    }

    @Test
    void shouldReturnValidationError() throws Exception {
        // Arrange: Simulate validation error on email field
        String invalidEmailRequest = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"invalid-email\",\"password\":\"ValidPassword123!\"}";

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType("application/json")
                        .content(invalidEmailRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("email must be a well-formed email address"));
    }
}