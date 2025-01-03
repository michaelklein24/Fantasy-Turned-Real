package com.kleintwins.ftr.auth.controller;

import com.kleintwins.ftr.auth.exception.AccountAlreadyExists;
import com.kleintwins.ftr.auth.exception.InvalidPassword;
import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthExceptionHandlerTest {

    private AuthExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new AuthExceptionHandler();
    }

    @Test
    void shouldHandleAccountAlreadyExists() {
        // Arrange
        AccountAlreadyExists exception = new AccountAlreadyExists("Account already exists");

        // Act
        CustomErrorResponse response = exceptionHandler.accountAlreadyExists(exception).getBody();

        // Assert
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Account already exists", response.getErrorMsg());
    }

    @Test
    void shouldHandleInvalidPassword() {
        // Arrange
        InvalidPassword exception = new InvalidPassword("Invalid password");

        // Act
        CustomErrorResponse response = exceptionHandler.invalidPassword(exception).getBody();

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Invalid password", response.getErrorMsg());
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        // Arrange
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);
        FieldError fieldError = new FieldError("object", "email", "must be a well-formed email address");
        when(bindingResult.getFieldErrors()).thenReturn(java.util.Collections.singletonList(fieldError));

        // Act
        CustomErrorResponse response = exceptionHandler.newResponseErrors(exception).getBody();

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("email must be a well-formed email address", response.getErrorMsg());
    }
}
