package com.kleintwins.ftr.auth.helper;

import com.kleintwins.ftr.core.service.ConfigService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtHelperTest {

    @Mock
    private ConfigService configService;

    @InjectMocks
    private JwtHelper jwtHelper;

    private String validToken;
    private String expiredToken;
    private String secretKey = "abcdefghijklmnopqrstuvwxyz1234567890";  // 256-bit key for HS256

    @BeforeEach
    void setUp() {
        // Arrange: Generate valid and expired tokens for testing
        validToken = Jwts.builder()
                .claim("userId", "12345")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // 1 hour
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();

        expiredToken = Jwts.builder()
                .claim("userId", "12345")
                .setExpiration(new Date(System.currentTimeMillis() - 3600000))  // 1 hour ago
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    @Test
    void testExtractExpiration() {
        // Arrange: Mock the behavior of ConfigService to return the secret key
        when(configService.getString("api.jwt.secret", "")).thenReturn(secretKey);

        // Act: When calling extractExpiration for the valid token
        Date expiration = jwtHelper.extractExpiration(validToken);

        // Assert: Assert that expiration is correctly set and is in the future
        assertNotNull(expiration);
        assertTrue(expiration.after(new Date()));
    }

    @Test
    void testExtractUserId() {
        // Arrange: Mock the behavior of ConfigService to return the secret key
        when(configService.getString("api.jwt.secret", "")).thenReturn(secretKey);

        // Act: When calling extractUserId for the valid token
        String userId = jwtHelper.extractUserId(validToken);

        // Assert: Assert that the userId is correctly extracted
        assertEquals("12345", userId);
    }

    @Test
    void testExtractUserIdClaimFromClaims() {
        // Arrange: Mock the behavior of ConfigService to return the secret key
        when(configService.getString("api.jwt.secret", "")).thenReturn(secretKey);

        // Act: When calling extractClaim for the userId from the valid token
        String userId = jwtHelper.extractClaim(validToken, claims -> claims.get("userId", String.class));

        // Assert: Assert that the userId claim is correctly extracted
        assertEquals("12345", userId);
    }

    @Test
    void validateTokenShouldReturnTrueIfTokenNotExpired() {
        // Arrange: Mock the behavior of ConfigService to enable expiration check
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(true);
        when(configService.getString("api.jwt.secret", "")).thenReturn(secretKey);

        // Act: When calling validateToken for a valid token
        Boolean isValid = jwtHelper.validateToken(validToken);

        // Assert: Assert that the token is valid (not expired)
        assertTrue(isValid);
    }

    @Test
    void validateTokenShouldReturnFalseIfTokenExpired() {
        // Arrange: Mock the behavior of ConfigService to enable expiration check
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(true);

        // Act: When calling validateToken for an expired token
        Boolean isValid = jwtHelper.validateToken(expiredToken);

        // Assert: Assert that the token is expired
        assertFalse(isValid);
    }

    @Test
    void validateTokenShouldReturnTrueIfExpirationIsDisabled() {
        // Arrange: Mock the behavior of ConfigService to disable expiration check
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(false);

        // Act: When calling validateToken for any token (expiration does not matter)
        Boolean isValid = jwtHelper.validateToken(expiredToken);

        // Assert: Assert that expiration does not affect the token validity
        assertTrue(isValid);
    }
}
