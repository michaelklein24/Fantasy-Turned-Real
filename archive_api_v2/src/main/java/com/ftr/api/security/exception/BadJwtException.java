package com.ftr.api.security.exception;

public class BadJwtException extends RuntimeException {
    public BadJwtException(String message) {
        super(message);
    }
}
