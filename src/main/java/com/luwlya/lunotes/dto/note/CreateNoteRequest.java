package com.luwlya.lunotes.dto.note;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateNoteRequest(
        @NotNull
        UUID authorId,
        @NotNull
        String title,
        @NotNull
        String text,
        List<String> tags
) {
}
