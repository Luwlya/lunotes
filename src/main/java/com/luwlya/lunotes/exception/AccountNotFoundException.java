package com.luwlya.lunotes.exception;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(UUID id) {
        super("Account " + id + " is not found.");
    }

    public AccountNotFoundException(String email) {
        super("Account with email: " + email + " is not found");
    }
}
