package com.kleintwins.ftr.league.controller;

import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.league.exception.ParticipantAlreadyInLeague;
import com.kleintwins.ftr.league.exception.UserAlreadyInvited;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(15)
public class LeagueExceptionHandler {

    @ExceptionHandler(value = UserAlreadyInvited.class)
    public ResponseEntity<CustomErrorResponse> userAlreadyInvitedException(UserAlreadyInvited e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.CONFLICT.toString(), e.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ParticipantAlreadyInLeague.class)
    public ResponseEntity<CustomErrorResponse> participantAlreadyInLeagueException(ParticipantAlreadyInLeague e) {
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.CONFLICT.toString(), e.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
