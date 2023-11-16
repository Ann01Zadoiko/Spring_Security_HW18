package com.example.please.controller;

import com.example.please.entity.Note;
import com.example.please.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("notes", noteService.listAll());
        return "list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        noteService.deleteById(id);
        return "redirect:/notes/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("note") Note note) {
        return "add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("note") Note note) {
        noteService.add(note);
        return "redirect:/notes/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("note", noteService.getById(id));
        return "edit";
    }

    @PostMapping("edit/{id}")
    public String update(@ModelAttribute("note") Note note){
        noteService.update(note);
        return "redirect:/notes/list";
    }
}
