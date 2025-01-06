package com.kleintwins.ftr.auth.controller;

import com.kleintwins.ftr.auth.exception.AccountAlreadyExists;
import com.kleintwins.ftr.auth.exception.InvalidPassword;
import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = AccountAlreadyExists.class)
    public ResponseEntity<CustomErrorResponse> accountAlreadyExists(AccountAlreadyExists e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.FORBIDDEN.toString(), e.getMessage());
        error.setStatus(HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = InvalidPassword.class)
    public ResponseEntity<CustomErrorResponse> invalidPassword(InvalidPassword e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> newResponseErrors(MethodArgumentNotValidException e) {
        // BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();

        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();

        // Translate the FieldErrors to CustomErrorResponse
        String errorMsg = fieldErrors.get(0).getField() + " " + fieldErrors.get(0).getDefaultMessage();
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.toString(), errorMsg);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        // Create and return the ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> badCredentials(BadCredentialsException e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.UNAUTHORIZED.toString(), e.getMessage());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }


}

