package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todolist.domain.event.Action;
import todolist.dto.card.RequestCardDto;
import todolist.dto.card.ResponseCardDto;
import todolist.dto.card.ResponseCardsDto;
import todolist.dto.event.EventDto;
import todolist.service.CardService;
import todolist.service.EventService;

@RestController
public class CardController {

    private final CardService cardService;
    private final EventService eventService;

    @Autowired
    public CardController(CardService cardService, EventService eventService) {
        this.cardService = cardService;
        this.eventService = eventService;
    }

    @GetMapping("/todos")
    public ResponseCardsDto getTodo() {
        return cardService.getCards();
    }

    @PostMapping("/todo")
    public ResponseCardDto add(@RequestBody RequestCardDto requestCardDto) {
        ResponseCardDto responseCardDto = cardService.addCard(requestCardDto);
        eventService.addEvent(new EventDto(responseCardDto), Action.ADD);
        return responseCardDto;
    }

    @PutMapping("/todo/{id}")
    public void update(@PathVariable Long id, @RequestBody RequestCardDto requestCardDto) {
        String prevSection = cardService.getPrevSection(id);
        ResponseCardDto responseCardDto = cardService.updateCard(id, requestCardDto);

        if (prevSection.equals(responseCardDto.getSection())) {
            eventService.addEvent(new EventDto(responseCardDto), Action.UPDATE);
        } else {
            eventService.addEvent(new EventDto(prevSection, responseCardDto), Action.MOVE);
        }
    }

    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable Long id) {
        ResponseCardDto responseCardDto = cardService.deleteCard(id);
        eventService.addEvent(new EventDto(responseCardDto), Action.REMOVE);
    }
}
