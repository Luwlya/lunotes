package com.luwlya.lunotes.dto.note;

import com.luwlya.lunotes.model.NoteVisibility;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record NoteDto(
        UUID id,
        UUID authorId,
        String title,
        String text,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        NoteVisibility noteVisibility,
        List<String> tags
) {
}
