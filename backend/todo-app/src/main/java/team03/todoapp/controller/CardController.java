package team03.todoapp.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team03.todoapp.Service.CardService;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.controller.dto.CardUpdateFormRequest;
import team03.todoapp.controller.dto.CardsResponse;

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

    @PatchMapping("/card/{cardId}")
    public void updateCard(@PathVariable("cardId") Long cardId,
        @RequestBody CardUpdateFormRequest cardUpdateFormRequest) {
        cardService.update(cardId, cardUpdateFormRequest);
    }

    @GetMapping("/cards")
    public CardsResponse getCards() {
        return cardService.findAll();
    }
}
