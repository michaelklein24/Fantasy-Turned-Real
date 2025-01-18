package com.kleintwins.ftr.auth.exception;

public class UserNotPermitted extends RuntimeException {
    public UserNotPermitted(String message) {
        super(message);
    }
}
