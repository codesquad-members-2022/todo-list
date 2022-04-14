package com.todolist.project.web;

import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardListRequestDto;
import com.todolist.project.web.dto.CardListResponseDto;
import com.todolist.project.web.dto.CardUpdateDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<CardListResponseDto> getCardList() {
        return cardService.findAll();
    }

    @GetMapping("/sort")
    public List<CardListResponseDto> getCardListByStatus(@RequestParam("status") String cardStatus) {
        return cardService.findByStatus(cardStatus);
    }

    @PostMapping
    public List<CardListResponseDto> add(@RequestBody CardAddDto dto) {
        cardService.addCard(dto);
        return cardService.findByStatus(dto.getCardStatus());
    }

    @DeleteMapping("/{id}")
    public List<CardListResponseDto> remove(@PathVariable Long id) {
        CardListRequestDto dto = cardService.findById(id);
        cardService.removeCard(id);
        return cardService.findByStatus(dto.getCardStatus());
    }

    @PutMapping("/{id}")
    public List<CardListResponseDto> update(@PathVariable Long id, @RequestBody CardUpdateDto dto) {
       cardService.updateCard(id, dto);
       return cardService.findByStatus(dto.getCardStatus());
    }
}
