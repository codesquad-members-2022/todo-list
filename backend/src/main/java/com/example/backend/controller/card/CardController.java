package com.example.backend.controller.card;

import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.domain.card.Card;
import com.example.backend.service.card.CardService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/cards/{id}")
    public CardDto cardDetailInquiry(@PathVariable Long id) {
        Card card = cardService.findById(id);
        return new CardDto(card);
    }

    @GetMapping("/cards")
    public Map<String, List<Card>> getCards() {
        return cardService.findAll();
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
