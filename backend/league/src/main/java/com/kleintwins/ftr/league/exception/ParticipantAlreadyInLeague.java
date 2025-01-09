package com.kleintwins.ftr.league.exception;

public class ParticipantAlreadyInLeague extends RuntimeException {
    public ParticipantAlreadyInLeague(String message) {
        super(message);
    }
}
