package com.todolist.project.web;

import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardListDto;
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
    public List<CardListDto> list() {
        return cardService.findAll();
    }

    @GetMapping("/{cardStatus}")
    public List<CardListDto> listByStatus(@PathVariable String cardStatus) {
        return cardService.findByStatus(cardStatus);
    }

    @PostMapping
    public List<CardListDto> add(@RequestBody CardAddDto dto) {
        cardService.addCard(dto);
        return cardService.findByStatus(dto.getCardStatus());
    }

    @DeleteMapping("/{id}")
    public List<CardListDto> remove(@PathVariable Long id) {
        CardListDto dto = cardService.findById(id);
        cardService.removeCard(id);
        return cardService.findByStatus(dto.getCardStatus());
    }

    @PutMapping("/{id}")
    public List<CardListDto> update(@PathVariable Long id, @RequestBody CardUpdateDto dto) {
       cardService.updateCard(id, dto);
       return cardService.findByStatus(dto.getCardStatus());
    }
}
