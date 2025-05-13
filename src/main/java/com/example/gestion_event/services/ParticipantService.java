package com.example.gestion_event.services;
import com.example.gestion_event.entities.Participant;
import java.util.List;

public interface ParticipantService {
    Participant saveParticipant(Participant participant);
    List<Participant> getAllParticipants();
    Participant getParticipantById(Long id);
    void deleteParticipant(Long id);
    void addEventToParticipant(Long participantId, Long eventId);
    void removeEventFromParticipant(Long participantId, Long eventId);
}