package com.example.backend.controller.card;

import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.domain.card.Card;
import com.example.backend.service.card.CardService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards/write")
    public CardDto writeCard(@RequestBody CardDto cardDto) {
        Card card = cardService.writeCard(cardDto);
        return new CardDto(card);
    }
}
