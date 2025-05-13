package com.example.gestion_event.entities;



import jakarta.persistence.*;
import java.util.List;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events;

    public Participant() {

    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Participant(String fullName, String email, List<Event> events) {
        this.fullName = fullName;
        this.email = email;
        this.events = events;
    }
}

