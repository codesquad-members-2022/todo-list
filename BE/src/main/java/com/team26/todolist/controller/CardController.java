package com.team26.todolist.controller;

import com.team26.todolist.dto.request.CardChangeLocationRequest;
import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;
import com.team26.todolist.service.CardService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{columnId}")
    public ResponseEntity<List<CardResponse>> getCards(@PathVariable Long columnId) {
        List<CardResponse> cards = cardService.findByColumnId(columnId);

        return ResponseEntity.ok()
                .body(cards);
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(
            @RequestBody CardRegistrationRequest cardRegistrationRequest) {
        CardResponse savedCard = cardService.addCard(cardRegistrationRequest);

        return ResponseEntity.ok()
                .body(savedCard);
    }

    @PutMapping
    public ResponseEntity<CardResponse> updateCard(
            @RequestBody CardUpdateRequest cardUpdateRequest) {
        CardResponse updatedCard = cardService.modifyCard(cardUpdateRequest);

        return ResponseEntity.ok()
                .body(updatedCard);
    }

    @PatchMapping
    public ResponseEntity<CardResponse> changeCardLocation(
            @RequestBody CardChangeLocationRequest cardChangeLocationRequest) {
        CardResponse movedCard = cardService.changeCardLocation(cardChangeLocationRequest);

        return ResponseEntity.ok()
                .body(movedCard);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCard(@RequestBody CardDeleteRequest cardDeleteRequest) {
        cardService.deleteCard(cardDeleteRequest);

        return ResponseEntity.noContent()
                .build();
    }
}
