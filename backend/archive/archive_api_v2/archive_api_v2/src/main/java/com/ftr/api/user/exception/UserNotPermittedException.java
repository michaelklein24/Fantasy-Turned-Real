package com.ftr.api.user.exception;

public class UserNotPermittedException extends RuntimeException {
    public UserNotPermittedException(String message) {
        super(message);
    }
}
