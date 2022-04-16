package com.example.backend.web.controller;

import com.example.backend.service.CardService;
import com.example.backend.web.dto.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsController {

    private final CardService cardService;

    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @ApiOperation(value = "Card 조회")
    @GetMapping("/cards")
    public Columns cardList() {
        return cardService.findAll();
    }

    @ApiOperation(value = "Card 생성")
    @PostMapping("/cards")
    public CardListResponseDto save(@RequestBody CardSaveRequestDto dto) {
        return cardService.save(dto);
    }

    @ApiOperation(value = "Card 수정")
    @PutMapping("/cards/{id}")
    public CardListResponseDto update(@PathVariable Long id, @RequestBody CardUpdateRequestDto dto) {
        return cardService.update(id, dto);
    }

    @ApiOperation(value = "Card 삭제")
    @DeleteMapping("/cards/{id}")
    public Long delete(@PathVariable Long id) {
        return cardService.delete(id);
    }

    @ApiOperation(value = "Card 이동")
    @PutMapping("/cards")
    public Columns move(@RequestBody CardMoveRequestDto dto) {
        return cardService.move(dto);
    }
}
