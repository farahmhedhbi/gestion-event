package com.example.gestion_event.controllers;

import com.example.gestion_event.entities.Participant;
import com.example.gestion_event.services.ParticipantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public String listParticipants(Model model) {
        List<Participant> participants = participantService.getAllParticipants();
        model.addAttribute("participants", participants);
        return "participants/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("participant", new Participant());
        return "participants/form";
    }

    @PostMapping
    public String createParticipant(@ModelAttribute Participant participant) {
        participantService.saveParticipant(participant);
        return "redirect:/participants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Participant participant = participantService.getParticipantById(id);
        model.addAttribute("participant", participant);
        return "participants/form";
    }

    @PostMapping("/update/{id}")
    public String updateParticipant(@PathVariable Long id, @ModelAttribute Participant participant) {
        participant.setId(id);
        participantService.saveParticipant(participant);
        return "redirect:/participants";
    }

    @GetMapping("/{id}")
    public String viewParticipant(@PathVariable Long id, Model model) {
        Participant participant = participantService.getParticipantById(id);
        model.addAttribute("participant", participant);
        return "participants/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return "redirect:/participants";
    }
}
