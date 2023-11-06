package com.luwlya.lunotes.repository;

import com.luwlya.lunotes.model.Note;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class NoteRepositoryImpl implements NoteRepository {

    private final JdbcTemplate jdbcTemplate;

    public NoteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Note note) {
        jdbcTemplate.update("INSERT INTO notes(id, author_id, title, text, created_at, updated_at, note_visibility, tags) " +
                "VALUES (?,?,?,?,?,?,?,?)",
        note.id(),
        note.authorId(),
        note.title(),
        note.text(),
        note.createdAt(),
        note.updatedAt(),
        note.noteVisibility().name(),
        note.tags().toArray(new String[0]));
    }

    @Override
    public Note get(UUID id) {
        return null;
    }

    @Override
    public List<Note> getAllNotes() {
        return null;
    }

    @Override
    public void update(Note updatedNote) {

    }

    @Override
    public boolean delete(Note note) {
        return false;
    }
}
