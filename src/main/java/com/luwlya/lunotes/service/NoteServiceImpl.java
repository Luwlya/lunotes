package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.note.CreateNoteRequest;
import com.luwlya.lunotes.dto.note.NoteDto;
import com.luwlya.lunotes.dto.note.NoteListDto;
import com.luwlya.lunotes.dto.note.UpdateNoteRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    @Override
    public NoteDto createNote(CreateNoteRequest request) {
        return null;
    }

    @Override
    public NoteDto getNote(UUID id) {
        return null;
    }

    @Override
    public NoteListDto getAllNotes() {
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
