package com.luwlya.lunotes.exception;

import java.util.UUID;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(UUID id) {
        super("Account " + id + " is already existed.");
    }
}
