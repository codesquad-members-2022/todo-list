package com.codesquad.todolist.card;

import com.codesquad.todolist.card.dto.CardUpdateRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.codesquad.todolist.card.dto.CardCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public void create(CardCreateRequest createRequest) {
        cardService.create(createRequest);
    }

    @PutMapping("/{id}")
    public void updateCard(@PathVariable(value = "id") Integer cardId,
        @RequestBody CardUpdateRequest request) {
        cardService.updateCard(cardId, request);
    }

}
