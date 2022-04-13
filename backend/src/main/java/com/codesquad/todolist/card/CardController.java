package com.codesquad.todolist.card;

import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardMoveRequest;
import com.codesquad.todolist.card.dto.CardUpdateRequest;
import com.codesquad.todolist.history.dto.HistoryResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
@Validated
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<HistoryResponse> createCard(
        @RequestBody @Valid CardCreateRequest request) {
        HistoryResponse historyResponse = cardService.create(request);
        return new ResponseEntity<>(historyResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public HistoryResponse updateCard(@PathVariable(value = "id") Integer cardId,
        @RequestBody @Valid CardUpdateRequest request) {
        return cardService.update(cardId, request);
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<?> moveCard(@PathVariable(value = "id") Integer cardId,
        @RequestBody @Valid CardMoveRequest request) {
        HistoryResponse historyResponse = cardService.move(cardId, request);
        return new ResponseEntity<>(historyResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable(value = "id") Integer cardId) {
        HistoryResponse historyResponse = cardService.delete(cardId);
        return new ResponseEntity<>(historyResponse, HttpStatus.NO_CONTENT);
    }

}
