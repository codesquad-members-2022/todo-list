package team03.todoapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team03.todoapp.Service.CardService;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.controller.dto.CardMoveFormRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CardController {

    private final Logger log = LoggerFactory.getLogger(CardController.class);
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
    public void moveCard(@Validated @RequestBody CardMoveFormRequest cardMoveFormRequest, BindingResult bindingResult, HttpServletResponse httpServletResponse, @PathVariable("cardId") Long cardId) {
        if (bindingResult.hasErrors()) {
            log.error("bindingResult: {}", bindingResult);
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        cardService.move(cardId, cardMoveFormRequest);
    }

}
