package com.webproject.notebook.controllers;

import com.webproject.notebook.models.Entry;
import com.webproject.notebook.repo.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping("/entry")
    public String entryMain(Model model) {
        Iterable<Entry> entries = entryRepository.findAll();
        model.addAttribute("entries", entries);
        return "entryMain";
    }
}
