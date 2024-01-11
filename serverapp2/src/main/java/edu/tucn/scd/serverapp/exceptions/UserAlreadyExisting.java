package edu.tucn.scd.serverapp.exceptions;

public class UserAlreadyExisting extends RuntimeException {
    public UserAlreadyExisting(String message) {
        super(message);
    }
}
