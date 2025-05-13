package com.example.gestion_event.services;
import com.example.gestion_event.entities.Event;
import java.util.List;

public interface EventService {
    Event saveEvent(Event event);
    List<Event> getAllEvents();
    Event getEventById(Long id);
    void deleteEvent(Long id);
    List<Event> getEventsByTitle(String title);
    void addParticipantToEvent(Long eventId, Long participantId);
    void removeParticipantFromEvent(Long eventId, Long participantId);
}

