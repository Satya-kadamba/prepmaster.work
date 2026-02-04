package com.example.eventmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name must not be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Location must not be blank")
    @Column(nullable = false)
    private String location;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    @Column(nullable = false)
    private LocalDate eventDate;

    @NotBlank(message = "Organizer name must not be blank")
    @Column(nullable = false)
    private String organizer;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
}
