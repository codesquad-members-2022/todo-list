package todolist.controller;

import org.springframework.web.bind.annotation.*;
import todolist.dto.card.RequestCardDto;
import todolist.dto.card.ResponseCardDto;
import todolist.dto.card.ResponseCardsDto;
import todolist.service.CardService;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/todos")
    public ResponseCardsDto getTodo() {
        return cardService.getCards();
    }

    @PostMapping("/todo")
    public ResponseCardDto add(@RequestBody RequestCardDto requestCardDto) {
        return cardService.addCard(requestCardDto);
    }

    @PutMapping("/todo/{id}")
    public void update(@PathVariable Long id, @RequestBody RequestCardDto requestCardDto) {
        cardService.updateCard(id, requestCardDto);
    }

    @DeleteMapping("/todo/{id}")
    public void delete(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
