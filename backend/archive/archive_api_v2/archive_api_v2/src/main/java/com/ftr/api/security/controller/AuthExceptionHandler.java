package com.ftr.api.security.controller;

import com.ftr.api.core.dto.CustomErrorResponse;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> badCredentials(BadCredentialsException e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.UNAUTHORIZED.toString(), e.getMessage());
        error.setStatus((HttpStatus.UNAUTHORIZED.value()));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<CustomErrorResponse> entityAlreadyExists(EntityExistsException e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.CONFLICT.toString(), e.getMessage());
        error.setStatus((HttpStatus.CONFLICT.value()));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> entityAlreadyExists(UsernameNotFoundException e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
        error.setStatus((HttpStatus.CONFLICT.value()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
