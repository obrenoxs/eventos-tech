package com.eventostech.api.controller;

import com.eventostech.api.domain.event.Event;
import com.eventostech.api.domain.event.EventRequestDTO;
import com.eventostech.api.domain.event.EventResponseDTO;
import com.eventostech.api.service.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("date") Long date,
            @RequestParam("city") String city,
            @RequestParam("uf") String uf,
            @RequestParam("remote") Boolean remote,
            @RequestParam("eventUrl") String eventUrl,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, date, city, uf, remote, eventUrl, image);
        Event newEvent = this.eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getUpComingEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<EventResponseDTO> allEvents = this.eventService.getUpComingEvents(page, size);
        return ResponseEntity.ok().body(allEvents);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDTO>> filterEvents(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(required = false) String title,
                                                               @RequestParam(required = false) String city,
                                                               @RequestParam(required = false) String uf,
                                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
                                                               ) {
        List<EventResponseDTO> events = eventService.getFilteredEvents(page, size, title, city, uf, startDate, endDate);
        return ResponseEntity.ok().body(events);
    }
}
