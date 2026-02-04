package com.example.eventmanagement.service.impl;

import com.example.eventmanagement.exception.ResourceNotFoundException;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found with id: " + id));
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found with id: " + id));

        existingEvent.setName(event.getName());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setEventDate(event.getEventDate());
        existingEvent.setOrganizer(event.getOrganizer());

        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
}
