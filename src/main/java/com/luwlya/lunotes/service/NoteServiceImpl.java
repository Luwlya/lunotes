package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.note.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    @Override
    public Note createNote(CreateNoteRequest request) {
        Note note = new Note(UUID.randomUUID(),
                UUID.randomUUID(),
                request.title(),
                request.text(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                Visibility.PUBLIC,
                request.tags());
        return dto(note);
    }

    private Note dto(Note note) {
        return new Note(
                note.id(),
                note.authorId(),
                note.title(),
                note.text(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                Visibility.PUBLIC,
                note.tags());
    }

    @Override
    public Note getNote(UUID id) {
        return null;
    }

    @Override
    public NoteList getAllNotes() {
        return null;
    }

    @Override
    public Note updateNote(UUID id, UpdateNoteRequest update) {
        return null;
    }

    @Override
    public void deleteNote(UUID id) {

    }
}
