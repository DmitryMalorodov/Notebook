package com.webproject.notebook.controllers;

import com.webproject.notebook.models.Entry;
import com.webproject.notebook.repo.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/entry/add")
    public String entryAdd(Model model) {
        return "entryAdd";
    }

    @PostMapping("/entry/add")
    public String entryAddPost(@RequestParam String name, @RequestParam String description, Model model) {
        Entry entry = new Entry(name, description);
        entryRepository.save(entry);
        return "redirect:/entry";
    }

    @GetMapping("/entry/{id}")
    public String entryDetails(@PathVariable(value = "id") long id, Model model) {
        if (!entryRepository.existsById(id)) {
            return "redirect:/entry";
        }

        Optional<Entry> entry = entryRepository.findById(id);
        List<Entry> result = new ArrayList<>();
        entry.ifPresent(result :: add);
        model.addAttribute("entry", result);
        return "entryDetails";
    }

    @GetMapping("/entry/{id}/edit")
    public String entryEdit(@PathVariable(value = "id") long id, Model model) {
        if (!entryRepository.existsById(id)) {
            return "redirect:/entry";
        }

        Optional<Entry> entry = entryRepository.findById(id);
        List<Entry> result = new ArrayList<>();
        entry.ifPresent(result :: add);
        model.addAttribute("entry", result);
        return "entryEdit";
    }

    @PostMapping("/entry/{id}/edit")
    public String entryEditPost(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String description, Model model) {
        Entry entry = entryRepository.findById(id).orElseThrow();

        entry.setName(name);
        entry.setDescription(description);
        entryRepository.save(entry);
        return "redirect:/entry";
    }

    @PostMapping("/entry/{id}/delete")
    public String entryDeletePost(@PathVariable(value = "id") long id, Model model) {
        Entry entry = entryRepository.findById(id).orElseThrow();
        entryRepository.delete(entry);
        return "redirect:/entry";
    }
}
