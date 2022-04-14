package com.example.backend.web.controller;

import com.example.backend.domain.ActionType;
import com.example.backend.domain.Card;
import com.example.backend.service.CardService;
import com.example.backend.service.LogService;
import com.example.backend.web.dto.*;
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
    public CardListResponseDto save(@RequestBody CardSaveRequestDto dto) {
        String title = dto.getTitle();
        String columnName = dto.getColumnName();
        LogSaveRequestDto logSaveRequestDto = new LogSaveRequestDto.Builder()
                .title(title)
                .curColumnName(columnName)
                .actionType(ActionType.ADD)
                .build();
        logService.save(logSaveRequestDto);

        return cardService.save(dto);
    }

    @ApiOperation(value = "Card 수정")
    @PutMapping("/cards/{id}")
    public CardListResponseDto update(@PathVariable Long id, @RequestBody CardUpdateRequestDto dto) {
        String title = dto.getTitle();
        LogSaveRequestDto logSaveRequestDto = new LogSaveRequestDto.Builder()
                .title(title)
                .actionType(ActionType.UPDATE)
                .build();
        logService.save(logSaveRequestDto);

        return cardService.update(id, dto);
    }

    @ApiOperation(value = "Card 삭제")
    @DeleteMapping("/cards/{id}")
    public Long delete(@PathVariable Long id) {
        Card card = cardService.findById(id);
        LogSaveRequestDto logSaveRequestDto = new LogSaveRequestDto.Builder()
                .title(card.getTitle())
                .curColumnName(card.getColumnName())
                .actionType(ActionType.REMOVE)
                .build();
        logService.save(logSaveRequestDto);

        return cardService.delete(id);
    }

    @ApiOperation(value = "Card 이동")
    @PutMapping("/cards")
    public Columns move(@RequestBody CardMoveRequestDto dto) {
        Long id = dto.getId();
        Card currentCard = cardService.findById(id);
        String title = currentCard.getTitle();
        String columnName = currentCard.getColumnName();

        if (!columnName.equals(dto.getNewColumnName())) {
            LogSaveRequestDto logSaveRequestDto = new LogSaveRequestDto.Builder()
                    .title(title)
                    .prevColumnName(columnName)
                    .curColumnName(dto.getNewColumnName())
                    .actionType(ActionType.MOVE)
                    .build();
            logService.save(logSaveRequestDto);
        }

        return cardService.move(dto);
    }
}
