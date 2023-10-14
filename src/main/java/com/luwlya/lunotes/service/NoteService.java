package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.note.CreateNoteRequest;
import com.luwlya.lunotes.dto.note.NoteDto;
import com.luwlya.lunotes.dto.note.NoteListDto;
import com.luwlya.lunotes.dto.note.UpdateNoteRequest;

import java.util.UUID;

public interface NoteService {
    NoteDto createNote(CreateNoteRequest request);

    NoteDto getNote(UUID id);

    NoteListDto getAllNotes();

    NoteDto updateNote(UUID id, UpdateNoteRequest update);

    void deleteNote(UUID id);
}
