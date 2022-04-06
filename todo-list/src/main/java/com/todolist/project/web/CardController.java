package com.todolist.project.web;

import com.todolist.project.domain.Status;
import com.todolist.project.domain.card.Card;
import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.addCardDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/card")
@RestController
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/add")
    public String add(addCardDto dto) {
        cardService.addCard(new Card(dto.getTitle(), dto.getContents(), dto.getWriter(), Status.DO));
        return "redirect:/";
    }

    @DeleteMapping("/remove/{id}")
    public String remove(int id) {
        cardService.removeCard(id);
        return "redirect:/";
    }
}
