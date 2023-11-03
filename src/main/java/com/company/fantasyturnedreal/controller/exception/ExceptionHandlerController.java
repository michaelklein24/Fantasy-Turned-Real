package com.company.fantasyturnedreal.controller.exception;

import com.company.fantasyturnedreal.exception.DataNotFoundException;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<CustomErrorResponse>> newResponseErrors(MethodArgumentNotValidException e) {

        // BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();

        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();

        // Translate the FieldErrors to CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.toString(), fieldError.getDefaultMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            errorResponseList.add(errorResponse);
        }

        // Create and return the ResponseEntity
        ResponseEntity<List<CustomErrorResponse>> responseEntity = new ResponseEntity<>(errorResponseList, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(value = {DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> dataNotFoundException(DataNotFoundException e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
        error.setStatus((HttpStatus.NOT_FOUND.value()));
        error.setTimestamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value = {MismatchingIdsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> dataNotFoundException(MismatchingIdsException e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
        error.setStatus((HttpStatus.BAD_REQUEST.value()));
        error.setTimestamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
