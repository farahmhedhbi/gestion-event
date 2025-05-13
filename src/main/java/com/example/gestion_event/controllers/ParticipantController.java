package com.example.gestion_event.controllers;

import com.example.gestion_event.entities.Event;
import com.example.gestion_event.entities.Participant;
import com.example.gestion_event.services.EventService;
import com.example.gestion_event.services.ParticipantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;
    private final EventService eventService;

    public ParticipantController(ParticipantService participantService,
                                 EventService eventService) {
        this.participantService = participantService;
        this.eventService = eventService;
    }

    @GetMapping
    public String listParticipants(Model model) {
        model.addAttribute("participants", participantService.getAllParticipants());
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
        model.addAttribute("participant", participantService.getParticipantById(id));
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
        model.addAttribute("participant", participantService.getParticipantById(id));
        return "participants/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return "redirect:/participants";
    }

    @GetMapping("/{id}/events")
    public String viewParticipantEvents(@PathVariable Long id, Model model) {
        Participant participant = participantService.getParticipantById(id);
        List<Event> allEvents = eventService.getAllEvents();

        model.addAttribute("participant", participant);
        model.addAttribute("allEvents", allEvents);
        return "participants/manage-events";
    }

    @PostMapping("/{participantId}/events/add")
    public String addEventToParticipant(@PathVariable Long participantId,
                                        @RequestParam Long eventId,
                                        RedirectAttributes redirectAttributes) {
        try {
            participantService.addEventToParticipant(participantId, eventId);
            redirectAttributes.addFlashAttribute("success", "Événement ajouté avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout: " + e.getMessage());
        }
        return "redirect:/participants/" + participantId + "/events";
    }

    @PostMapping("/{participantId}/events/remove")
    public String removeEventFromParticipant(@PathVariable Long participantId,
                                             @RequestParam Long eventId,
                                             RedirectAttributes redirectAttributes) {
        try {
            participantService.removeEventFromParticipant(participantId, eventId);
            redirectAttributes.addFlashAttribute("success", "Événement retiré avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors du retrait: " + e.getMessage());
        }
        return "redirect:/participants/" + participantId + "/events";
    }
}