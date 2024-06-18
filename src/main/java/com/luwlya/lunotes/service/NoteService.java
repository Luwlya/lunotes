package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.note.CreateNoteRequest;
import com.luwlya.lunotes.dto.note.NoteDto;
import com.luwlya.lunotes.dto.note.NoteDtoList;
import com.luwlya.lunotes.dto.note.UpdateNoteRequest;

import java.util.UUID;

public interface NoteService {
    NoteDto createNote(CreateNoteRequest request);

    NoteDto getNote(UUID id);

    NoteDtoList getAllNotes(String title, String tag, String text);

    NoteDto updateNote(UUID id, UpdateNoteRequest update);

    void deleteNote(UUID id);
}
