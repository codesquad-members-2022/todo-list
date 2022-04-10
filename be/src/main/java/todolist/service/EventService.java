package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.domain.Action;
import todolist.domain.Event;
import todolist.dto.ResponseCardDto;
import todolist.repository.EventMemoryRepository;

import java.util.List;

@Service
public class EventService {

    private EventMemoryRepository repository;

    @Autowired
    public EventService(EventMemoryRepository repository) {
        this.repository = repository;
    }

    public void addEvent(ResponseCardDto responseCardDto, Action action) {
        Long cardId = responseCardDto.getId();
        String cardSection = responseCardDto.getSection();
        repository.save(new Event(cardId, cardSection, action));
    }

    public List<Event> getEventList() {
        return repository.findAll();
    }
}
