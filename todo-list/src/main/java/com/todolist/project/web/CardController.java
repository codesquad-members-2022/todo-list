package com.todolist.project.web;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.card.Card;
import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.CardAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/card")
@RestController
public class CardController {
    private final CardService cardService;

    @PostMapping("/add")
    public String add(CardAddDto dto) {
        cardService.addCard(new Card(dto.getTitle(), dto.getContents(), dto.getWriter(), CardStatus.DO));
        return "redirect:/";
    }

    @DeleteMapping("/remove/{id}")
    public String remove(int id) {
        cardService.removeCard(id);
        return "redirect:/";
    }
}
