package com.example.eventmanagement;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event event;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        event = new Event();
        event.setName("Tech Event");
        event.setLocation("Chennai");
        event.setOrganizer("OpenAI");
    }

    @Test
    void getEventById_shouldReturnEvent() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event result = eventService.getEventById(1L);

        assertNotNull(result);
        assertEquals("Tech Event", result.getName());
        verify(eventRepository, times(1)).findById(1L);
    }
}
