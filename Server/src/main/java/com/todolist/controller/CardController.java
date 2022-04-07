package com.todolist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.domain.dto.CardCreateDto;
import com.todolist.domain.dto.CardInformationDto;
import com.todolist.domain.dto.CardPatchDto;
import com.todolist.domain.dto.CardResponseDto;
import com.todolist.service.CardService;

@RequestMapping("/todolist")
@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public Map<String, List<CardInformationDto>> read() {
        return cardService.findAllCards(1);
    }

    @PostMapping
    public ResponseEntity<CardResponseDto> save(@RequestBody CardCreateDto cardCreateDto) {
        Integer cardId = cardService.save(cardCreateDto);
        return ResponseEntity.ok(new CardResponseDto(cardId));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<CardResponseDto> delete(@PathVariable Integer cardId) {
        Integer deletedId = cardService.delete(cardId);
        return ResponseEntity.ok(new CardResponseDto(deletedId));
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardInformationDto> findCard(@PathVariable Integer cardId) {
        CardInformationDto cardInformation = cardService.findCard(cardId);
        return ResponseEntity.ok(cardInformation);
    }

    @PatchMapping("/{cardId}")
    public ResponseEntity<CardResponseDto> patch(@PathVariable Integer cardId, @RequestBody CardPatchDto cardPatchDto) {
        cardService.patch(cardId, cardPatchDto);
        return ResponseEntity.ok(new CardResponseDto(cardId));
    }
}
