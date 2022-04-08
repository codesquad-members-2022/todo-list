package com.todolist.project.web;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.card.Card;
import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.CardAddDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/card")
@RestController
public class CardController {
    private final CardService cardService;
    private static int RESULT;

    @GetMapping
    public List<Card> home() {
        List<Card> cards = cardService.findAll();
        return cards;
    }

    @PostMapping()
    public String add(CardAddDto dto) {
        RESULT = cardService.addCard(new Card(dto.getTitle(), dto.getContents(), dto.getWriter(), CardStatus.TODO));
        if (RESULT == 0) {
            //TODO: 에러 페이지나 경고창으로 처리하게끔
        }
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String remove(int id) {
        RESULT = cardService.removeCard(id);
        if (RESULT == 0) {
            //TODO: 에러 페이지나 경고창으로 처리하게끔
        }
        return "redirect:/";
    }
}
