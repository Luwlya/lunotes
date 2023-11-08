package com.luwlya.lunotes.http;

import com.luwlya.lunotes.dto.note.CreateNoteRequest;
import com.luwlya.lunotes.dto.note.NoteDto;
import com.luwlya.lunotes.dto.note.NoteDtoList;
import com.luwlya.lunotes.dto.note.UpdateNoteRequest;
import com.luwlya.lunotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteDto> createNote(@RequestBody @Validated CreateNoteRequest request) {
        NoteDto noteDto = noteService.createNote(request);
        return ResponseEntity.ok().body(noteDto);
    }

    @GetMapping("/notes")
    public ResponseEntity<NoteDtoList> getAllNotes(){
        NoteDtoList listDto = noteService.getAllNotes();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDto> getNote(@PathVariable UUID id) {
        NoteDto dto = noteService.getNote(id);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/notes/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable UUID id,
                                                   @RequestBody UpdateNoteRequest update) {
        NoteDto noteDto = noteService.updateNote(id, update);
        return ResponseEntity.ok().body(noteDto);
    }
}
