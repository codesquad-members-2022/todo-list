package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todolist.dto.RequestCardDto;
import todolist.dto.ResponseCardDto;
import todolist.service.CardService;

@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
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
