package com.example.gestion_event.controllers;

import com.example.gestion_event.entities.Organizer;
import com.example.gestion_event.services.OrganizerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public String getAllOrganizers(Model model) {
        List<Organizer> organizers = organizerService.getAllOrganizers();
        model.addAttribute("organizers", organizers);
        return "organizers/list";
    }

    @GetMapping("/{id}")
    public String getOrganizerById(@PathVariable Long id, Model model) {
        Organizer organizer = organizerService.getOrganizerById(id);
        model.addAttribute("organizer", organizer);
        return "organizers/view";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("organizer", new Organizer());
        return "organizers/form";
    }

    @PostMapping("/create")
    public String createOrganizer(@ModelAttribute Organizer organizer) {
        if (organizer.getId() != null) {
            organizer.setId(null);
        }
        organizerService.saveOrganizer(organizer);
        return "redirect:/organizers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Organizer organizer = organizerService.getOrganizerById(id);
        model.addAttribute("organizer", organizer);
        return "organizers/form";
    }

    @PostMapping("/update/{id}")
    public String updateOrganizer(@PathVariable Long id, @ModelAttribute Organizer organizer) {
        organizer.setId(id);
        organizerService.saveOrganizer(organizer);
        return "redirect:/organizers";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrganizer(@PathVariable Long id) {
        organizerService.deleteOrganizer(id);
        return "redirect:/organizers";
    }
}