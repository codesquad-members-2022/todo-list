package team03.todoapp.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team03.todoapp.Service.CardService;
import team03.todoapp.controller.dto.CardAddFormRequest;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card")
    public Long addCard(@Validated @ModelAttribute CardAddFormRequest cardAddFormRequest) {
        Long cardId = cardService.add(cardAddFormRequest);
        return cardId;
    }

    @DeleteMapping("/card/{cardId}")
    public void removeCard(@PathVariable("cardId") Long cardId) {
        cardService.remove(cardId);
    }

}
