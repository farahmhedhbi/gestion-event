package com.example.gestion_event.services;



import com.example.gestion_event.entities.Organizer;

import java.util.List;

public interface OrganizerService {
    Organizer saveOrganizer(Organizer organizer);
    List<Organizer> getAllOrganizers();
    Organizer getOrganizerById(Long id);
    void deleteOrganizer(Long id);
}

