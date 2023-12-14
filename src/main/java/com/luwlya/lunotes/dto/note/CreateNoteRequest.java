package com.luwlya.lunotes.dto.note;

import com.luwlya.lunotes.model.NoteVisibility;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateNoteRequest(
        @NotNull
        String title,
        @NotNull
        String text,
        @NotNull
        NoteVisibility visibility,
        List<String> tags
) {
}
