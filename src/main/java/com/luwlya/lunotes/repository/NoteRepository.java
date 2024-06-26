package com.luwlya.lunotes.repository;

import com.luwlya.lunotes.model.Note;

import java.util.List;
import java.util.UUID;

public interface NoteRepository {
    void insert(Note note);

    Note get(UUID id);

    List<Note> getAllNotes(UUID authorId, String title, String tag, String text);

    void update(Note updatedNote);

    boolean delete(UUID id);
}
