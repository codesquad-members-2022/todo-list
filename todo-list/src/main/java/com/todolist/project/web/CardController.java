package com.todolist.project.web;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.card.Card;
import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/card")
@RestController
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<Card> home() {
        List<Card> cards = cardService.findAll();
        return cards;
    }

    @PostMapping
    public int add(@RequestBody CardAddDto dto) {
        return cardService.addCard(dto);
    }

    @DeleteMapping("/{id}")
    public int remove(@PathVariable Long id) {
        return cardService.removeCard(id);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Long id, @RequestBody CardUpdateDto dto) {
        return cardService.updateCard(id, dto);
    }
}
