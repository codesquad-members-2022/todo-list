package com.ijava.todolist.card.controller;

import com.ijava.todolist.card.controller.dto.*;
import com.ijava.todolist.card.service.CardActionService;
import com.ijava.todolist.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CardActionService cardActionService;

    @GetMapping("/cards")
    public List<CardResponse> cardList(@RequestParam(value="columnId") Long columnId) {
        return cardService.findCardList(columnId)
                .stream()
                .map(CardResponse::from)
                .sorted(Comparator.comparing(CardResponse::getCreatedDate))
                .collect(Collectors.toUnmodifiableList());
    }

    @PostMapping("/cards")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CardResponse createCard(@RequestBody CardCreateRequest cardCreateRequest) {
        return cardActionService.add(cardCreateRequest);
    }

    @PutMapping("/cards/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCard(@PathVariable("id") Long id, @RequestBody CardUpdateRequest updateRequest) {
        cardActionService.update(id, updateRequest);
    }

    @PatchMapping("/cards")
    public CardMovedResponse moveCard(@RequestBody CardMoveRequest cardMoveRequest) {
        return cardActionService.move(cardMoveRequest);
    }

    @DeleteMapping("/cards/{id}")
    public CardDeleteResponse deleteCard(@PathVariable("id") Long id) {
        return cardActionService.delete(id);
    }
}
