package com.kleintwins.ftr.league.exception;

public class UserAlreadyInvited extends RuntimeException {
  public UserAlreadyInvited(String message) {
    super(message);
  }
}
