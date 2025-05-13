package com.example.gestion_event.services.impl;

import com.example.gestion_event.entities.Event;
import com.example.gestion_event.entities.Participant;
import com.example.gestion_event.repository.EventRepository;
import com.example.gestion_event.repository.ParticipantRepository;
import com.example.gestion_event.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository participantRepository,
                                  EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant : " + id));
    }

    @Override
    public void deleteParticipant(Long id) {
        Participant participant = getParticipantById(id);
        participant.getEvents().forEach(event ->
                event.getParticipants().remove(participant));
        participantRepository.delete(participant);
    }

    @Override
    public void addEventToParticipant(Long participantId, Long eventId) {
        Participant participant = getParticipantById(participantId);
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        if (!participant.getEvents().contains(event)) {
            participant.getEvents().add(event);
            event.getParticipants().add(participant);
        }
    }

    @Override
    public void removeEventFromParticipant(Long participantId, Long eventId) {
        Participant participant = getParticipantById(participantId);
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        if (participant.getEvents().contains(event)) {
            participant.getEvents().remove(event);
            event.getParticipants().remove(participant);
        }
    }
}