package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todolist.domain.Action;
import todolist.domain.Card;
import todolist.dto.RequestCardDto;
import todolist.dto.ResponseCardDto;
import todolist.service.CardService;
import todolist.service.EventService;

import java.util.List;
import java.util.Map;

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
    public Map<String, List<Card>> getTodo() {
        return cardService.getCards();
    }

    @PostMapping("/todo")
    public ResponseCardDto add(@RequestBody RequestCardDto requestCardDto) {
        ResponseCardDto responseCardDto = cardService.addCard(requestCardDto);
        eventService.addEvent(responseCardDto, Action.ADD);
        return responseCardDto;
    }

    @PutMapping("/todo/{id}")
    public void put(@PathVariable Long id, @RequestBody RequestCardDto requestCardDto) {
        cardService.updateCard(id, requestCardDto);
    }

    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
