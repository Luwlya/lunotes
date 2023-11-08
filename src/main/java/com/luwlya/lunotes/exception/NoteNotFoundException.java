package com.luwlya.lunotes.exception;

import java.util.UUID;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(UUID id) {
        super("Note " + id + " is not found.");
    }
}
