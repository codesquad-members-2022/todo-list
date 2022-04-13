package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import todolist.domain.event.Event;
import todolist.dto.event.ResponseEventDto;
import todolist.service.EventService;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<ResponseEventDto> getEvents() {
        return eventService.getEventList();
    }
}
