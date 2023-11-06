package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.note.CreateNoteRequest;
import com.luwlya.lunotes.dto.note.NoteDto;
import com.luwlya.lunotes.dto.note.NoteDtoList;
import com.luwlya.lunotes.dto.note.UpdateNoteRequest;
import com.luwlya.lunotes.model.Note;
import com.luwlya.lunotes.model.NoteVisibility;
import com.luwlya.lunotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final Clock clock;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, Clock clock) {
        this.noteRepository = noteRepository;
        this.clock = clock;
    }

    @Override
    public NoteDto createNote(CreateNoteRequest request) {
        Note note = new Note(UUID.randomUUID(),
                request.authorId(),
                request.title(),
                request.text(),
                OffsetDateTime.now(clock),
                OffsetDateTime.now(clock),
                NoteVisibility.PUBLIC,
                request.tags());
        noteRepository.insert(note);
        return dto(note);
    }

    private NoteDto dto(Note note) {
        return new NoteDto(
                note.id(),
                note.authorId(),
                note.title(),
                note.text(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                NoteVisibility.PUBLIC,
                note.tags());
    }

    @Override
    public NoteDto getNote(UUID id) {
        Note note = noteRepository.get(id);
        return dto(note);
    }

    @Override
    public NoteDtoList getAllNotes() {
        return null;
    }

    @Override
    public NoteDto updateNote(UUID id, UpdateNoteRequest update) {
        return null;
    }

    @Override
    public void deleteNote(UUID id) {

    }
}
