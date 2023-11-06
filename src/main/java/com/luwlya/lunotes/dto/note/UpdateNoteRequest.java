package com.luwlya.lunotes.dto.note;

import jakarta.validation.constraints.NotNull;

public record UpdateNoteRequest (
        @NotNull
        String title,
        @NotNull
        String text,
        Visibility visibility){
}
