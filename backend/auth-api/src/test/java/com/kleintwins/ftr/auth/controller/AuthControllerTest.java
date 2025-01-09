package com.kleintwins.ftr.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kleintwins.ftr.auth.dto.LoginUserRequest;
import com.kleintwins.ftr.auth.dto.LoginUserResponse;
import com.kleintwins.ftr.auth.dto.RegisterUserRequest;
import com.kleintwins.ftr.auth.dto.RegisterUserResponse;
import com.kleintwins.ftr.auth.service.AuthService;
import com.kleintwins.ftr.auth.service.TokenService;
import com.kleintwins.ftr.auth.exception.AccountAlreadyExists;
import com.kleintwins.ftr.user.exception.InvalidPassword;
import com.kleintwins.ftr.core.service.I18nService;
import com.kleintwins.ftr.user.model.UserModel;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = {AuthController.class, AuthExceptionHandler.class})
@AutoConfigureMockMvc
@EnableWebMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private I18nService i18nService;

    private Validator validator;

    private RegisterUserRequest validRequest;
    private LoginUserRequest loginUserRequest;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        validRequest = new RegisterUserRequest("John", "Doe", "john.doe@example.com", "ValidPassword123!");
        loginUserRequest = new LoginUserRequest("tony.stark@test.com", "incorrectPassword");
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @WithMockUser
    void registerApishouldRegisterUserAndReturnToken() throws Exception {
        // Arrange
        String expectedToken = "mockToken";
        RegisterUserResponse response = new RegisterUserResponse(expectedToken);

        when(authService.registerUser(any(), any(), any(), any())).thenReturn(new UserModel());
        when(tokenService.generateToken(any())).thenReturn(expectedToken);

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").value(expectedToken));

        // Verify interactions
        verify(authService).registerUser(any(), any(), any(), any());
        verify(tokenService).generateToken(any());
    }

    @Test
    @WithMockUser
    void registerApiShouldReturn403ForEmailAlreadyExistsError() throws Exception {
        // Arrange
        when(authService.registerUser(any(), any(), eq("john.doe@example.com"), any())).thenThrow(new AccountAlreadyExists("Account already exists"));

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(403))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMsg").value("Account already exists"));
    }

    @Test
    @WithMockUser
    void registerApiShouldReturn400ForInvalidPasswordError() throws Exception {
        // Arrange
        when(authService.registerUser(any(), any(), any(), any())).thenThrow(new InvalidPassword("Invalid password"));

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMsg").value("Invalid password"));
    }

    @Test
    void registerApiShouldReturn400ForRequestValidationError() throws Exception {
        // Arrange: Simulate validation error on email field
        RegisterUserRequest requestWithInvalidEmail = SerializationUtils.clone(validRequest);
        requestWithInvalidEmail.setEmail("invalid-email");

        // Act & Assert
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(requestWithInvalidEmail)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMsg").value("email must be a well-formed email address"));
    }

    @Test
    void loginApiShouldReturn401WhenReceivingBadCredentials() throws Exception {
        // Arrange: throw Bad Credentials exception
        when(authService.loginUser(any(), any())).thenThrow(new BadCredentialsException("bad credentials"));

        // Act and Assert
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(loginUserRequest)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(401))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMsg").value("bad credentials"));
    }

    @Test
    void loginApiShouldSuccessfullyLogUserIn() throws Exception {
        // Arrange
        String expectedToken = "mockToken";
        LoginUserResponse response = new LoginUserResponse(expectedToken);

        when(authService.loginUser(any(), any())).thenReturn(new UserModel());
        when(tokenService.generateToken(any())).thenReturn(expectedToken);

        // Act & Assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").value(expectedToken));

        // Verify interactions
        verify(authService).loginUser(any(), any());
        verify(tokenService).generateToken(any());
    }


}
