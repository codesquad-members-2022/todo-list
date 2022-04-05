package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.dto.CardDto;
import kr.codesquad.todolist.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/mockup")
public class mockupController {

    private final CardService cardService;

    public mockupController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> findOne() {
        Card card1 = new Card(1L, "ron2", 1, "subject", "contents", LocalDateTime.now(), LocalDateTime.now(), false);
        Card card2 = new Card(2L, "vans", 2, "subject", "contents", LocalDateTime.now(), LocalDateTime.now(), false);
        ResponseEntity<List<Card>> cardResponseEntity = new ResponseEntity<>(List.of(card1, card2), HttpStatus.OK);

        return cardResponseEntity;
    }

    @PostMapping("/card/new")
    public ResponseEntity<CardDto> create(@RequestBody CardDto cardDto) {
        System.out.println(cardDto.toString());

        return new ResponseEntity<>(cardDto, HttpStatus.OK);
    }


}
