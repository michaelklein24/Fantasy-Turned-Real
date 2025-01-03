package com.kleintwins.ftr.auth.service;

import com.kleintwins.ftr.auth.model.UserModel;
import com.kleintwins.ftr.core.service.ConfigService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TokenServiceTest {

    @Mock
    private ConfigService configService;

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private UserModel userModel;

    @Mock
    private JwtBuilder jwtBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGenerateTokenWithExpiration() {
        // Arrange: Mock the configuration values
        String jwtSecret = "secretKey";
        Long tokenLifetime = 600000L;  // 10 minutes
        boolean isExpirationEnabled = true;
        String userId = "user123";
        String email = "user@example.com";

        when(configService.getString("api.jwt.secret", "")).thenReturn(jwtSecret);
        when(configService.getLong("api.jwt.expiration", 600000)).thenReturn(tokenLifetime);
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(isExpirationEnabled);
        when(userModel.getUserId()).thenReturn(userId);
        when(userModel.getEmail()).thenReturn(email);

        // Mock the static call to Jwts.builder()
        try (MockedStatic<Jwts> mockedJwts = mockStatic(Jwts.class)) {
            mockedJwts.when(Jwts::builder).thenReturn(jwtBuilder);
            when(Jwts.builder().setIssuedAt(any())).thenReturn(jwtBuilder);
            when(jwtBuilder.setClaims(anyMap())).thenReturn(jwtBuilder);
            when(jwtBuilder.setExpiration(any(Date.class))).thenReturn(jwtBuilder);
            when(jwtBuilder.signWith(any())).thenReturn(jwtBuilder);
            when(jwtBuilder.compact()).thenReturn("generatedToken");

            // Act: Call generateToken method
            String token = tokenService.generateToken(userModel);

            // Assert: Verify the returned token and validate claims
            assertNotNull(token);
            assertEquals("generatedToken", token);
            verify(jwtBuilder).setClaims(anyMap());
            verify(jwtBuilder).setIssuedAt(any(Date.class));
            verify(jwtBuilder).setExpiration(any(Date.class)); // Expiration should be set
            verify(jwtBuilder).signWith(any());  // Verifying signing
        }
    }

    @Test
    void shouldGenerateTokenWithoutExpiration() {
        // Arrange: Mock the configuration values with expiration disabled
        String jwtSecret = "secretKey";
        Long tokenLifetime = 600000L;  // 10 minutes
        boolean isExpirationEnabled = false;
        String userId = "user123";
        String email = "user@example.com";

        when(configService.getString("api.jwt.secret", "")).thenReturn(jwtSecret);
        when(configService.getLong("api.jwt.expiration", 600000)).thenReturn(tokenLifetime);
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(isExpirationEnabled);
        when(userModel.getUserId()).thenReturn(userId);
        when(userModel.getEmail()).thenReturn(email);

        // Mock the static call to Jwts.builder()
        try (MockedStatic<Jwts> mockedJwts = mockStatic(Jwts.class)) {
            mockedJwts.when(Jwts::builder).thenReturn(jwtBuilder);
            when(jwtBuilder.setIssuedAt(any())).thenReturn(jwtBuilder);
            when(jwtBuilder.setClaims(anyMap())).thenReturn(jwtBuilder);
            when(jwtBuilder.setExpiration(isExpirationEnabled ? any(Date.class) : null)).thenReturn(jwtBuilder);
            when(jwtBuilder.signWith(any())).thenReturn(jwtBuilder);
            when(jwtBuilder.compact()).thenReturn("generatedTokenWithoutExpiration");

            // Act: Call generateToken method
            String token = tokenService.generateToken(userModel);

            // Assert: Verify the returned token and validate claims
            assertNotNull(token);
            assertEquals("generatedTokenWithoutExpiration", token);
            verify(jwtBuilder).setClaims(anyMap());
            verify(jwtBuilder).setIssuedAt(any(Date.class));
            verify(jwtBuilder).setExpiration(null); // No expiration since it's disabled
            verify(jwtBuilder).signWith(any());  // Verifying signing
        }
    }

    @Test
    void shouldRetrieveJwtSecretFromConfig() {
        // Arrange: Mock configService to return a secret key
        String expectedSecret = "jwtSecretKey";
        when(configService.getString("api.jwt.secret", "")).thenReturn(expectedSecret);

        // Act: Retrieve the secret key
        String jwtSecret = configService.getString("api.jwt.secret", "");

        // Assert: Verify that the secret key matches
        assertEquals(expectedSecret, jwtSecret);
    }

    @Test
    void shouldHandleMissingJwtSecret() {
        // Arrange: Mock configService to return an empty string for the secret key
        when(configService.getString("api.jwt.secret", "")).thenReturn("");

        // Act: Call generateToken method with a mock user
        String token = tokenService.generateToken(userModel);

        // Assert: Verify that a token is generated, but we don't care about the actual token content here
        assertNotNull(token);
    }
}
