package com.example.gestion_event.services.impl;



import com.example.gestion_event.entities.Participant;
import com.example.gestion_event.repository.ParticipantRepository;
import com.example.gestion_event.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}

