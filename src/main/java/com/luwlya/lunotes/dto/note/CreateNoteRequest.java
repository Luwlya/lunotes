package com.luwlya.lunotes.dto.note;

import com.luwlya.lunotes.model.NoteVisibility;
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
        @NotNull
        NoteVisibility visibility,
        List<String> tags
) {
}
