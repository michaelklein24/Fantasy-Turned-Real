package com.kleintwins.ftr.auth.helper;

import com.kleintwins.ftr.core.service.ConfigService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtHelperTest {

    @Mock
    private ConfigService configService;

    @InjectMocks
    private JwtHelper jwtHelper;

    @Mock
    private Claims claims;

    @Mock
    private JwtParser jwtParser;

    private String token;
    private String secretKey = "secretKey";
    private String userId = "123";
    private String username = "user@example.com";
    private Date expirationDate = new Date(System.currentTimeMillis() + 600000L); // 10 minutes from now

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup a mock token and claims
        token = "mockedToken";

        // Mock configService calls
        when(configService.getString("api.jwt.secret", "")).thenReturn(secretKey);
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(true);

        // Mock claims extraction
        when(claims.getSubject()).thenReturn(username);
        when(claims.getExpiration()).thenReturn(expirationDate);
        when(claims.get("userId", Integer.class)).thenReturn(Integer.valueOf(userId));
    }

    @Test
    void shouldExtractUsernameFromToken() {
        // Arrange
        when(configService.getString("api.jwt.secret", "")).thenReturn(secretKey);
        JwtParser mockParser = mock(JwtParser.class);
        when(mockParser.parseClaimsJws(token)).thenReturn(mock(Jws.class));
        when(mockParser.parseClaimsJws(token).getBody()).thenReturn(claims);

        // Act
        String usernameFromToken = jwtHelper.extractUsername(token);

        // Assert
        assertEquals(username, usernameFromToken);
    }

    @Test
    void shouldExtractExpirationFromToken() {
        // Arrange
        when(configService.getString("api.jwt.secret", "")).thenReturn(secretKey);
        JwtParser mockParser = mock(JwtParser.class);
        when(mockParser.parseClaimsJws(token)).thenReturn(mock(Jws.class));
        when(mockParser.parseClaimsJws(token).getBody()).thenReturn(claims);

        // Act
        Date expirationFromToken = jwtHelper.extractExpiration(token);

        // Assert
        assertEquals(expirationDate, expirationFromToken);
    }

    @Test
    void shouldExtractUserIdFromToken() {
        // Arrange
        when(configService.getString("api.jwt.secret", "")).thenReturn(secretKey);
        JwtParser mockParser = mock(JwtParser.class);
        when(mockParser.parseClaimsJws(token)).thenReturn(mock(Jws.class));
        when(mockParser.parseClaimsJws(token).getBody()).thenReturn(claims);

        // Act
        Integer userIdFromToken = jwtHelper.extractUserId(token);

        // Assert
        assertEquals(Integer.valueOf(userId), userIdFromToken);
    }

    @Test
    void shouldValidateTokenAsNotExpired() {
        // Arrange: Mock isTokenExpired to return false
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(true);
        when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() + 100000L)); // Future expiration date

        // Act
        boolean isValid = jwtHelper.validateToken(token);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void shouldValidateTokenAsExpired() {
        // Arrange: Mock expiration date to be in the past
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(true);
        when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() - 100000L)); // Past expiration date

        // Act
        boolean isValid = jwtHelper.validateToken(token);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void shouldValidateTokenAsNotExpiredWhenExpirationDisabled() {
        // Arrange: Disable expiration
        when(configService.getBool("api.jwt.expiration.enabled", true)).thenReturn(false);

        // Act
        boolean isValid = jwtHelper.validateToken(token);

        // Assert: Token should be valid regardless of expiration date
        assertTrue(isValid);
    }
}
