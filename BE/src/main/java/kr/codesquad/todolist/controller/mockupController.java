package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.domain.Section;
import kr.codesquad.todolist.dto.card.CardResponse;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.dto.card.UpdateCardRequest;
import kr.codesquad.todolist.dto.section.CardsOfSection;
import kr.codesquad.todolist.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class mockupController {
    private final Logger log = LoggerFactory.getLogger(mockupController.class);

    private final CardService cardService;

    public mockupController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<CardResponse> create(@RequestBody CreateCardRequest cardRequest) {

        CardResponse cardResponse = cardService.create(cardRequest);

        return new ResponseEntity<>(cardResponse, HttpStatus.CREATED);
    }

//    @GetMapping("/cards")
//    public ResponseEntity<List<CardResponse>> findAll() {
//        List<CardResponse> cards = cardService.findAll();
//
//        return new ResponseEntity<>(cards, HttpStatus.OK);
//    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardResponse> findOne(@PathVariable Long id) {
        CardResponse cardResponse = cardService.findOne(id);

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean isDeleted = cardService.delete(id);

        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @PatchMapping("/cards/{id}")
    public ResponseEntity<CardResponse> updateOne(@PathVariable Long id, @RequestBody UpdateCardRequest cardRequest) {

        CardResponse cardResponse = cardService.update(cardRequest);

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @GetMapping("/cards/{sectionId}")
    public ResponseEntity<CardsOfSection> findCardsBySectionId(@PathVariable Integer sectionId) {
        //SectionService필요
        Section todo = new Section(sectionId, "TO DO");
        List<CardResponse> cards = cardService.findCardsOfSection(sectionId);

        return new ResponseEntity<>(new CardsOfSection(todo, cards), HttpStatus.OK);
    }

}
