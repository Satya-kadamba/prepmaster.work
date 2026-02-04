package com.example.eventmanagement.controller;

import com.example.eventmanagement.dto.EventRequestDto;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(
            @Valid @RequestBody EventRequestDto dto) {

        Event event = new Event();
        event.setName(dto.getName());
        event.setLocation(dto.getLocation());
        event.setEventDate(dto.getEventDate());
        event.setOrganizer(dto.getOrganizer());

        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequestDto dto) {

        Event event = new Event();
        event.setName(dto.getName());
        event.setLocation(dto.getLocation());
        event.setEventDate(dto.getEventDate());
        event.setOrganizer(dto.getOrganizer());

        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
