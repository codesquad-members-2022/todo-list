package com.todolist.controller;

import com.todolist.domain.dto.CardInformationDto;
import com.todolist.service.CardService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/todolist")
@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public Map<String, List<CardInformationDto>> read() {
        return cardService.findAllCards();
    }
}
