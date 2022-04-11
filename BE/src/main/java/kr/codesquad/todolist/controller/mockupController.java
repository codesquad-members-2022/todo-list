package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.dto.CardDto;
import kr.codesquad.todolist.dto.CardResponse;
import kr.codesquad.todolist.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mockup")
public class mockupController {
    private final Logger log = LoggerFactory.getLogger(mockupController.class);
    private final CardService cardService;

    public mockupController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<CardResponse> create(@RequestBody CardDto cardDto) {
        log.info(cardDto.toString());
        CardResponse cardResponse = cardService.create(cardDto);

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardResponse>> findAll() {
        List<CardResponse> cards = cardService.findAll();

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardResponse> findOne(@PathVariable Long id) {
        CardResponse card = cardService.findOne(id);

        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean isDeleted = cardService.delete(id);

        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

}
