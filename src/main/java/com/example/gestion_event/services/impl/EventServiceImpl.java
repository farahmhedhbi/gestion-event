package com.example.gestion_event.services.impl;

import com.example.gestion_event.entities.Event;
import com.example.gestion_event.entities.Participant;
import com.example.gestion_event.repository.EventRepository;
import com.example.gestion_event.services.EventService;
import com.example.gestion_event.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantService participantService;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        if (event != null) {
            for (Participant participant : event.getParticipants()) {
                participant.getEvents().remove(event);
            }
            eventRepository.deleteById(id);
        }
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title);
    }


    @Transactional
    public void addParticipantToEvent(Long eventId, Long participantId) {
        Event event = getEventById(eventId);
        Participant participant = participantService.getParticipantById(participantId);

        if (event != null && participant != null) {
            event.getParticipants().add(participant);
            participant.getEvents().add(event);
            eventRepository.save(event);
        }
    }


    @Transactional
    public void removeParticipantFromEvent(Long eventId, Long participantId) {
        Event event = getEventById(eventId);
        Participant participant = participantService.getParticipantById(participantId);

        if (event != null && participant != null) {
            event.getParticipants().remove(participant);
            participant.getEvents().remove(event);
            eventRepository.save(event);
        }
    }
}