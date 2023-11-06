package com.luwlya.lunotes.dto.note;

import jakarta.validation.constraints.NotNull;

public record CreateNoteRequest(
        @NotNull
        String title,
        @NotNull
        String text,
        String [] tags
) {
}
