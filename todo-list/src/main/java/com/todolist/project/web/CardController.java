package com.todolist.project.web;

import com.todolist.project.domain.card.Card;
import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<Card> list() {
        return cardService.findAll();
    }

    @GetMapping("/{cardStatus}")
    public List<Card> listByStatus(@PathVariable String cardStatus) { return cardService.findByStatus(cardStatus);}

    @PostMapping
    public int add(@RequestBody CardAddDto dto) {
        return cardService.addCard(dto, cardService.findAll().size());
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
