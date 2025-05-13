package com.example.gestion_event.repository;



import com.example.gestion_event.entities.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Organizer findByEmail(String email);
}
