package com.luwlya.lunotes.repository;

import com.luwlya.lunotes.model.Note;
import com.luwlya.lunotes.model.NoteVisibility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.*;

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
        return jdbcTemplate.queryForObject(
                "SELECT * FROM notes WHERE id = ?",
                this::extractNote,
                id);
    }

    private Note extractNote(ResultSet rs, int i) throws SQLException {
        return new Note(rs.getObject("id", UUID.class),
                rs.getObject("author_id", UUID.class),
                rs.getString("title"),
                rs.getString("text"),
                rs.getObject("created_at", OffsetDateTime.class),
                rs.getObject("updated_at", OffsetDateTime.class),
                NoteVisibility.valueOf(rs.getString("note_visibility")),
                Collections.singletonList(rs.getArray("tags").toString()));
    }

    @Override
    public List<Note> getAllNotes(UUID authorId, String title, String tag, String text) {
        String query = "SELECT * FROM notes WHERE author_id = ?";
        if (title != null) {
            query = query + " AND title = ?";
        }
        if (tag != null) {
            query = query + " AND arraycontains(tags, ?)";
        }
        if (text != null) {
            query += " AND text LIKE ?";
            text = "%" + text + "%";
        }
        Object[] args = Arrays.asList(authorId, title, tag, text).stream().filter(Objects::nonNull).toArray();
        return jdbcTemplate.query(query, this::extractNote, args);
    }

    @Override
    public void update(Note note) {
        jdbcTemplate.update(
                "UPDATE notes SET title = ?, text = ?, tags = ? WHERE id = ?",
                note.title(),
                note.text(),
                note.tags().toArray(new String[0]),
                note.id());
    }

    @Override
    public boolean delete(UUID id) {
        String SQL = "DELETE FROM notes WHERE id = ?";
        Object[] args = new Object[]{id};
        System.out.println("Note " + id + "has been successfully deleted");
        return jdbcTemplate.update(SQL, args) == 1;
    }
}
