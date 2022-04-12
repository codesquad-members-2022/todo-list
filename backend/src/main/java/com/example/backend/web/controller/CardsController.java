package com.example.backend.web.controller;

import com.example.backend.domain.ActionType;
import com.example.backend.service.LogService;
import com.example.backend.web.dto.*;
import com.example.backend.service.CardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsController {

    private final CardService cardService;
    private final LogService logService;

    public CardsController(CardService cardService, LogService logService) {
        this.cardService = cardService;
        this.logService = logService;
    }

    @ApiOperation(value = "Card 조회")
    @GetMapping("/cards")
    public Columns cardList() {
        return cardService.findAll();
    }

    @ApiOperation(value = "Card 생성")
    @PostMapping("/cards")
    public Long save(@RequestBody CardSaveRequestDto dto) {
        String title = dto.getTitle();
        String columnName = dto.getColumnName();

        logService.save(new LogSaveRequestDto(title, null, columnName, ActionType.ADD));
        return cardService.save(dto);
    }

    @ApiOperation(value = "Card 수정")
    @PutMapping("/cards/{id}")
    public Long update(@PathVariable Long id, @RequestBody CardUpdateRequestDto dto) {
        return cardService.update(id, dto);
    }

    @ApiOperation(value = "Card 삭제")
    @DeleteMapping("/cards/{id}")
    public Long delete(@PathVariable Long id) {
        return cardService.delete(id);
    }

    @ApiOperation(value = "Card 이동")
    @PutMapping("/cards")
    public Long move(@RequestBody CardMoveRequestDto dto) {
        return cardService.move(dto);
    }
}
