package com.example.backend.controller.card;

import com.example.backend.controller.ApiResult;
import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.controller.card.dto.CardSaveResponse;
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

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public ApiResult<CardDto> writeCard(@RequestBody @Valid CardDto cardDto) {
        Card card = cardService.writeCard(cardDto);
        return ApiResult.OK(new CardDto(card));
    }

    @GetMapping("/cards/{id}")
    public ApiResult<CardDto> cardDetailInquiry(@PathVariable Long id) {
        Card card = cardService.findById(id);
        return ApiResult.OK(new CardDto(card));
    }

    @GetMapping("/cards")
    public ApiResult<Map<CardType, List<CardSaveResponse>>> getCards() {
        return ApiResult.OK(cardService.findAll());
    }

    @DeleteMapping("/cards/{id}")
    public void delete(@PathVariable Long id) {
        cardService.delete(id);
    }

    @PatchMapping({"/cards/{id}"})
    public ApiResult<CardDto> updateCard(@PathVariable Long id, @RequestBody CardDto cardDto) {
        Card card = cardService.updateCard(id, cardDto);
        return ApiResult.OK(new CardDto(card));
    }
}
