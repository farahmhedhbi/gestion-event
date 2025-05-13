package com.example.gestion_event.repository;



import com.example.gestion_event.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Participant findByEmail(String email);
}
