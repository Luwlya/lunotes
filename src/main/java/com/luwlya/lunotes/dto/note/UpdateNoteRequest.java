package com.luwlya.lunotes.dto.note;

import com.luwlya.lunotes.model.NoteVisibility;

public record UpdateNoteRequest (
        String title,
        String text,
        NoteVisibility noteVisibility){
}
