package com.luwlya.lunotes.dto.note;

import java.time.OffsetDateTime;
import java.util.UUID;

public record Note(
        UUID id,
        UUID authorId,
        String title,
        String text,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        Visibility visibility,
        String [] tags
        ) {
}
