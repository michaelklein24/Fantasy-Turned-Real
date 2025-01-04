package com.kleintwins.ftr.auth.exception;

public class InvalidPassword extends RuntimeException {
    public InvalidPassword(String message) {
        super(message);
    }
}
