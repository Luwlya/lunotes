package com.luwlya.lunotes.http;

import com.luwlya.lunotes.dto.note.CreateNoteRequest;
import com.luwlya.lunotes.dto.note.NoteDto;
import com.luwlya.lunotes.dto.note.NoteDtoList;
import com.luwlya.lunotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
