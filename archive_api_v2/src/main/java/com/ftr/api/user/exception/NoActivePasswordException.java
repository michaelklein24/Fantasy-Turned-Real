package com.ftr.api.user.exception;

public class NoActivePasswordException extends RuntimeException {
    public NoActivePasswordException(Integer userId) {
        super(String.format("User with userId '%s' has no active password set", userId.toString()));
    }
}
