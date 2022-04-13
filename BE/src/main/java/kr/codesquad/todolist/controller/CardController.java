package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.domain.Section;
import kr.codesquad.todolist.dto.card.CardResponse;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.dto.card.MotionInfo;
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
public class CardController {
    private final Logger log = LoggerFactory.getLogger(CardController.class);

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<CardResponse> create(@RequestBody CreateCardRequest cardRequest) {

        CardResponse cardResponse = cardService.create(cardRequest);

        return new ResponseEntity<>(cardResponse, HttpStatus.CREATED);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardsOfSection>> findAll() {
        List<CardsOfSection> all = cardService.findAllbySections();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

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

        CardResponse cardResponse = cardService.update(id, cardRequest);

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @GetMapping("/cards/section/{sectionId}")
    public ResponseEntity<CardsOfSection> findCardsBySectionId(@PathVariable Integer sectionId) {

        Section section = cardService.findSection(sectionId);
        List<CardResponse> cards = cardService.findCardsOfSection(sectionId);

        return new ResponseEntity<>(new CardsOfSection(section, cards), HttpStatus.OK);
    }

    @PatchMapping("/cards/move/{movingCardId}")
    public ResponseEntity<Boolean> moveOne(@PathVariable Long movingCardId, @RequestBody MotionInfo motionInfo) {
        boolean isMoved = cardService.move(motionInfo.getSectionId(), motionInfo.getTargetCardId(), movingCardId);
        return new ResponseEntity<>(isMoved, HttpStatus.OK);
    }

}
