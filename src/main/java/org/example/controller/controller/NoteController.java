package org.example.controller.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.example.service.dto.NoteDto;
import org.example.service.exception.NoteNotFoundException;
import org.example.service.mapper.NoteMapper;
import org.example.service.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Slf4j
@Validated
@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired private NoteService noteService;
    @Autowired private NoteMapper noteMapper;

    @GetMapping("/list")
    public ModelAndView noteList() {
        ModelAndView result = new ModelAndView("notes/noteList");
        result.addObject("notes", noteMapper.toNoteResponses(noteService.listAll()));
        return result;
    }

    @GetMapping("/edit")
    public ModelAndView getNoteForEdit(@NotEmpty @RequestParam(value="id") String id) throws NoteNotFoundException {
        UUID uuid = UUID.fromString(id);
        ModelAndView result = new ModelAndView("notes/editNoteById");
        result.addObject("note", noteMapper.toNoteResponse(noteService.getById(uuid)));
        return result;
    }
    @PostMapping("/create")
    public ModelAndView createNote(
            @RequestParam(value="title") @Size(min = 1, max = 250) String title,
            @RequestParam(value="content") @NotBlank String content) {
        NoteDto dto = new NoteDto();
        dto.setTitle(title);
        dto.setContent(content);
        noteService.add(dto);
        return noteList();
    }

    @PostMapping("/update")
    public ModelAndView updateNote(
            @NotEmpty @RequestParam(value="id") String id,
            @Size(min = 1, max = 250) @RequestParam(value="title") String title,
            @NotEmpty @RequestParam(value="content") String content) throws NoteNotFoundException {
        NoteDto dto = new NoteDto();
        dto.setId(UUID.fromString(id));
        dto.setTitle(title);
        dto.setContent(content);
        noteService.update(dto);
        return noteList();
    }

    @PostMapping("/delete")
    public ModelAndView deleteNoteById(@Valid @NotNull @RequestParam(value="id") String id) throws NoteNotFoundException {
        noteService.deleteById(UUID.fromString(id));
        return noteList();
    }



    /* Helpers */

    @GetMapping("/create")
    private ModelAndView CreateNoteForm() {
        return new ModelAndView("notes/createNote");
    }

    @GetMapping("/delete")
    private ModelAndView DeleteNoteForm() {
        return new ModelAndView("notes/deleteNote");
    }

    @GetMapping("/update")
    private ModelAndView updateNoteForm(){
        return new ModelAndView("notes/updateNote");
    }

}
