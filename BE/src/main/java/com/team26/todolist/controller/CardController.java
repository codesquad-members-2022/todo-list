package com.team26.todolist.controller;

import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardMoveRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;
import com.team26.todolist.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> getCards(@RequestParam String cardStatus) {
        //TODO
        // cardStatus가 null이거나 비어있을 때 예외처리
        if (cardStatus.equals(" ")) {

        }

        List<CardResponse> cards = cardService.findByCardStatus(cardStatus);

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
    public ResponseEntity<CardResponse> moveCard(@RequestBody CardMoveRequest cardMoveRequest) {
        CardResponse movedCard = cardService.moveCard(cardMoveRequest);

        return ResponseEntity.ok()
                .body(movedCard);
    }

    @DeleteMapping
    public ResponseEntity deleteCard(@RequestBody CardDeleteRequest cardDeleteRequest) {
        cardService.deleteCard(cardDeleteRequest);

        return ResponseEntity.noContent()
                .build();
    }
}
