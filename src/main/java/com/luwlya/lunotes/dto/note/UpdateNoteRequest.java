package com.luwlya.lunotes.dto.note;

import com.luwlya.lunotes.model.NoteVisibility;

import java.util.List;

public record UpdateNoteRequest (
        String title,
        String text,
        List<String> tags,
        NoteVisibility noteVisibility){
}
