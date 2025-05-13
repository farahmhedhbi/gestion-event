package com.example.gestion_event.controllers;

import com.example.gestion_event.entities.Event;
import com.example.gestion_event.entities.Participant;
import com.example.gestion_event.services.EventService;
import com.example.gestion_event.services.OrganizerService;
import com.example.gestion_event.services.ParticipantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final OrganizerService organizerService;
    private final ParticipantService participantService;

    public EventController(EventService eventService,
                           OrganizerService organizerService,
                           ParticipantService participantService) {
        this.eventService = eventService;
        this.organizerService = organizerService;
        this.participantService = participantService;
    }

    @GetMapping
    public String getAllEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events/list";
    }

    @GetMapping("/{id}")
    public String getEventById(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "events/view";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("organizers", organizerService.getAllOrganizers());
        return "events/form";
    }

    @PostMapping
    public String createEvent(@ModelAttribute Event event) {
        eventService.saveEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        model.addAttribute("organizers", organizerService.getAllOrganizers());
        return "events/form";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event event) {
        event.setId(id);
        eventService.saveEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("/{id}/participants")
    public String manageEventParticipants(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        List<Participant> allParticipants = participantService.getAllParticipants();

        model.addAttribute("event", event);
        model.addAttribute("allParticipants", allParticipants);
        return "events/manage-participants";
    }

    @PostMapping("/{eventId}/participants/add")
    public String addParticipantToEvent(@PathVariable Long eventId,
                                        @RequestParam Long participantId) {
        eventService.addParticipantToEvent(eventId, participantId);
        return "redirect:/events/" + eventId + "/participants";
    }

    @PostMapping("/{eventId}/participants/remove")
    public String removeParticipantFromEvent(@PathVariable Long eventId,
                                             @RequestParam Long participantId) {
        eventService.removeParticipantFromEvent(eventId, participantId);
        return "redirect:/events/" + eventId + "/participants";
    }
}

