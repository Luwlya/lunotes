package com.luwlya.lunotes.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record Note(
        UUID id,
        UUID authorId,
        String title,
        String text,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        NoteVisibility noteVisibility,
        List<String> tags) {
}
