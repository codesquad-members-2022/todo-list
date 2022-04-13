package com.team26.todolist.controller;

import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardMoveRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;
import com.team26.todolist.exception.EmptyCardStatusException;
import com.team26.todolist.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{columnId}")
    public ResponseEntity<List<CardResponse>> getCards(@PathVariable Long columnId) {
        //TODO
        // validation 적용예정
        if (columnId == null) {
            throw new EmptyCardStatusException("columnId는 비어있을 수 없습니다.");
        }

        List<CardResponse> cards = cardService.findByColumnId(columnId);

        return ResponseEntity.ok()
                .body(cards);
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody CardRegistrationRequest cardRegistrationRequest) {
        CardResponse savedCard = cardService.addCard(cardRegistrationRequest);

        return ResponseEntity.ok()
                .body(savedCard);
    }

    @PutMapping
    public ResponseEntity<CardResponse> updateCard(@RequestBody CardUpdateRequest cardUpdateRequest) {
        CardResponse updatedCard = cardService.modifyCard(cardUpdateRequest);

        return ResponseEntity.ok()
                .body(updatedCard);
    }

    @PatchMapping
    public ResponseEntity<CardResponse> changeCardStatus(@RequestBody CardMoveRequest cardMoveRequest) {
        CardResponse movedCard = cardService.changeCardLocation(cardMoveRequest);

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
