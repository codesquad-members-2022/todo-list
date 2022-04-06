package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.dto.CardDto;
import kr.codesquad.todolist.dto.CardResponse;
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
    public ResponseEntity<List<CardResponse>> findOne() {
        CardResponse card1 = new CardResponse(1L, 1, "ron2", "subject", "contents", LocalDateTime.now());
        CardResponse card2 = new CardResponse(2L,  2,"vans", "subject", "contents", LocalDateTime.now());
        ResponseEntity<List<CardResponse>> cardResponseEntity = new ResponseEntity<>(List.of(card1, card2), HttpStatus.OK);

        return cardResponseEntity;
    }

    @PostMapping("/card/new")
    public ResponseEntity<CardDto> create(@RequestBody CardDto cardDto) {
        System.out.println(cardDto.toString());

        return new ResponseEntity<>(cardDto, HttpStatus.OK);
    }


}
