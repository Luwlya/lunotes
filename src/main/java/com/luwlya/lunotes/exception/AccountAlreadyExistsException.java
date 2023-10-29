package com.luwlya.lunotes.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String email) {
        super("Account " + email + " is already existed.");
    }
}
