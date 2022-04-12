package team03.todoapp.controller;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team03.todoapp.Service.CardService;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.controller.dto.CardAddIdResponse;
import team03.todoapp.controller.dto.CardMoveFormRequest;
import team03.todoapp.controller.dto.CardUpdateFormRequest;
import team03.todoapp.controller.dto.CardsResponse;

@RestController
public class CardController {

    private final Logger log = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card")
    public CardAddIdResponse addCard(@Validated @RequestBody CardAddFormRequest cardAddFormRequest) {
        Long cardId = cardService.add(cardAddFormRequest);
        CardAddIdResponse cardAddIdResponse = new CardAddIdResponse(cardId);
        return cardAddIdResponse;
    }

    @DeleteMapping("/card/{cardId}")
    public void removeCard(@PathVariable("cardId") Long cardId) {
        cardService.remove(cardId);
    }

    @PatchMapping("/card/move/{cardId}")
    public void moveCard(@Validated @RequestBody CardMoveFormRequest cardMoveFormRequest,
        BindingResult bindingResult, HttpServletResponse httpServletResponse,
        @PathVariable("cardId") Long cardId) {
        if (bindingResult.hasErrors()) {
            log.error("bindingResult: {}", bindingResult);
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        cardService.move(cardId, cardMoveFormRequest);
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
