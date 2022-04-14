package com.example.backend.controller.card;

import com.example.backend.controller.ApiResult;
import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.controller.card.dto.CardSaveResponse;
import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import com.example.backend.service.card.CardReadResponse;
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
@RequestMapping("api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ApiResult<CardSaveResponse> writeCard(@RequestBody @Valid CardDto cardDto) {
        Card card = cardService.writeCard(cardDto);
        return ApiResult.OK(new CardSaveResponse(card));
    }

    @GetMapping("{id}")
    public ApiResult<CardDto> cardDetailInquiry(@PathVariable Long id) {
        Card card = cardService.findById(id);
        return ApiResult.OK(new CardDto(card));
    }

    @GetMapping
    public ApiResult<Map<CardType, List<CardReadResponse>>> getCards() {
        return ApiResult.OK(cardService.findAll());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        cardService.delete(id);
    }

    @PatchMapping({"{id}"})
    public ApiResult<CardDto> updateCard(@PathVariable Long id, @RequestBody CardDto cardDto) {
        Card card = cardService.updateCard(id, cardDto);
        return ApiResult.OK(new CardDto(card));
    }
}
