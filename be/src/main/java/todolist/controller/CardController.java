package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todolist.domain.Card;
import todolist.dto.RequestCardDto;
import todolist.dto.ResponseCardDto;
import todolist.service.CardService;

import java.util.List;
import java.util.Map;

@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/todos")
    public Map<String, List<Card>> getTodo() {
        return cardService.getCards();
    }

    @PostMapping("/todo")
    public ResponseCardDto add(@RequestBody RequestCardDto requestCardDto) {
        return cardService.addCard(requestCardDto);
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
