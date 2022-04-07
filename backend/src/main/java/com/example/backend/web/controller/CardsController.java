package com.example.backend.web.controller;

import com.example.backend.domain.Column;
import com.example.backend.service.CardsService;
import com.example.backend.web.dto.CardSaveRequestDto;
import com.example.backend.web.dto.CardsMoveRequestDto;
import com.example.backend.web.dto.CardsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsController {

    private final CardsService cardsService;

    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    @GetMapping("/cards")
    public Column cardList() {
        return cardsService.findAll();
    }

    @PostMapping("/cards")
    public Long save(@RequestBody CardSaveRequestDto dto) {
        return cardsService.save(dto);
    }

    @PutMapping("/cards/{id}")
    public Long update(@PathVariable Long id, @RequestBody CardsUpdateRequestDto dto) {
        return cardsService.update(dto);
    }

    @DeleteMapping("/cards/{id}")
    public Long delete(@PathVariable Long id) {
        return cardsService.delete(id);
    }

    @PutMapping("/cards")
    public Long move(@RequestBody CardsMoveRequestDto dto) {
        return cardsService.move(dto);
    }
}
