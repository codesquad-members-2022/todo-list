package com.example.backend.web.controller;

import com.example.backend.domain.Column;
import com.example.backend.service.CardService;
import com.example.backend.web.dto.CardSaveRequestDto;
import com.example.backend.web.dto.CardMoveRequestDto;
import com.example.backend.web.dto.CardUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsController {

    private final CardService cardService;

    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public Column cardList() {
        return cardService.findAll();
    }

    @PostMapping("/cards")
    public Long save(@RequestBody CardSaveRequestDto dto) {
        return cardService.save(dto);
    }

    @PutMapping("/cards/{id}")
    public Long update(@PathVariable Long id, @RequestBody CardUpdateRequestDto dto) {
        return cardService.update(id, dto);
    }

    @DeleteMapping("/cards/{id}")
    public Long delete(@PathVariable Long id) {
        return cardService.delete(id);
    }

    @PutMapping("/cards")
    public Long move(@RequestBody CardMoveRequestDto dto) {
        return cardService.move(dto);
    }
}
