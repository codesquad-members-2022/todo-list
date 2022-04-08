package com.example.backend.controller.card;

import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import com.example.backend.service.card.CardService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/cards")
    public Map<String, List<Card>> getCards() {
        return cardService.findAll();
    }

    @GetMapping("/cards/{type}")
    public List<CardDto> getCard(@PathVariable CardType type) {
        List<Card> cards = cardService.findByType(type);
        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card : cards) {
            cardDtos.add(new CardDto(card));
        }
        return cardDtos;
    }

    @DeleteMapping("/cards/{id}")
    public CardDto delete(@PathVariable Long id) {
        cardService.delete(id);
        return null;
    }

    @PatchMapping({"/cards/{id}"})
    public CardDto updateCard(@PathVariable Long id, @RequestBody CardDto cardDto) {
        cardService.updateCard(id, cardDto);
        return null;
    }
}
