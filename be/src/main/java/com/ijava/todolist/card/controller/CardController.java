package com.ijava.todolist.card.controller;

import com.ijava.todolist.card.controller.dto.CardResponse;
import com.ijava.todolist.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/cards")
    public List<CardResponse> cardList(@RequestParam(value="columnId") Long columnId) {
        return cardService.findCardList(columnId)
                .stream()
                .map(CardResponse::from)
                .sorted(Comparator.comparing(CardResponse::getCreatedDate))
                .collect(Collectors.toUnmodifiableList());
    }

}
