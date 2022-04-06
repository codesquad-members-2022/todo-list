package com.team26.todolist.controller;

import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardMoveRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;
import com.team26.todolist.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardResponse>> getCards(@RequestParam String cardStatus) {
        List<CardResponse> cards = cardService.findByCardStatus(cardStatus);

        return ResponseEntity.ok()
                .body(cards);
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody CardRegistrationRequest cardRegistrationRequest) {
        CardResponse savedCard = cardService.join(cardRegistrationRequest);

        return ResponseEntity.ok()
                .body(savedCard);
    }

    @PatchMapping
    public ResponseEntity<CardResponse> updateCard(@RequestBody CardUpdateRequest cardUpdateRequest) {
        CardResponse updatedCard = cardService.update(cardUpdateRequest);

        return ResponseEntity.ok()
                .body(updatedCard);
    }

    @PatchMapping("/{newStatus}")
    public ResponseEntity<CardResponse> moveCard(@RequestBody CardMoveRequest cardMoveRequest, @PathVariable String newStatus) {
        CardResponse movedCard = cardService.move(cardMoveRequest);

        return ResponseEntity.ok()
                .body(movedCard);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCard(@RequestBody CardDeleteRequest cardDeleteRequest) {
        cardService.delete(cardDeleteRequest);

        return ResponseEntity.noContent()
                .build();
    }

}
