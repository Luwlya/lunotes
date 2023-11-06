package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.note.CreateNoteRequest;
import com.luwlya.lunotes.dto.note.Note;
import com.luwlya.lunotes.dto.note.NoteList;
import com.luwlya.lunotes.dto.note.UpdateNoteRequest;

import java.util.UUID;

public interface NoteService {
    Note createNote(CreateNoteRequest request);

    Note getNote(UUID id);

    NoteList getAllNotes();

    Note updateNote(UUID id, UpdateNoteRequest update);

    void deleteNote(UUID id);
}
