package com.example.gestion_event.services.impl;

import com.example.gestion_event.entities.Organizer;
import com.example.gestion_event.repository.OrganizerRepository;
import com.example.gestion_event.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    public Organizer saveOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    @Override
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    @Override
    public Organizer getOrganizerById(Long id) {
        return organizerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }
}