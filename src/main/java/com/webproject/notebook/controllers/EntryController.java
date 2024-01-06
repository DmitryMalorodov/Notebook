package com.webproject.notebook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryController {

    @GetMapping("/entry")
    public String entryMain(Model model) {
        return "entryMain";
    }
}
