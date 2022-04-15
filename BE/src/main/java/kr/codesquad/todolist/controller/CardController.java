package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.domain.Section;
import kr.codesquad.todolist.dto.card.CardResponse;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.dto.card.MotionInfo;
import kr.codesquad.todolist.dto.card.UpdateCardRequest;
import kr.codesquad.todolist.dto.log.LogRequest;
import kr.codesquad.todolist.dto.section.CardsOfSection;
import kr.codesquad.todolist.service.CardService;
import kr.codesquad.todolist.service.LogService;
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
    private final LogService logService;

    public CardController(CardService cardService, LogService logService) {
        this.cardService = cardService;
        this.logService = logService;
    }

    @PostMapping("/cards")
    public ResponseEntity<CardResponse> create(@RequestBody CreateCardRequest cardRequest) {
        log.info("REQUEST create card : {}", cardRequest);
        CardResponse cardResponse = cardService.create(cardRequest);

        LogRequest logRequest = LogRequest.addRequest(cardRequest);
        logService.save(logRequest);

        return new ResponseEntity<>(cardResponse, HttpStatus.CREATED);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardsOfSection>> findAll() {
        log.info("REQUEST get categorized cards all");
        List<CardsOfSection> all = cardService.findAllbySections();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardResponse> findOne(@PathVariable Long id) {
        log.info("REQUEST find card, id : {}", id);
        CardResponse cardResponse = cardService.findOne(id);

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("REQUEST delete card, id : {}", id);
        CardResponse currentCard = cardService.findOne(id);
        LogRequest logRequest = LogRequest.deleteRequest(currentCard);
        boolean isDeleted = cardService.delete(id);
        logService.save(logRequest);

        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @PatchMapping("/cards/{id}")
    public ResponseEntity<CardResponse> updateOne(@PathVariable Long id, @RequestBody UpdateCardRequest cardRequest) {
        log.info("REQUEST update card, id : {}, updateInfo : {}", id, cardRequest);
        CardResponse currentCard = cardService.findOne(id);
        CardResponse cardResponse = cardService.update(id, cardRequest);

        LogRequest logRequest = LogRequest.updateRequest(currentCard, cardResponse);
        logService.save(logRequest);

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @GetMapping("/cards/section/{sectionId}")
    public ResponseEntity<CardsOfSection> findCardsBySectionId(@PathVariable Integer sectionId) {
        log.info("REQUEST categorized cards by section, sectionId : {}", sectionId);
        Section section = cardService.findSection(sectionId);
        List<CardResponse> cards = cardService.findCardsOfSection(sectionId);

        return new ResponseEntity<>(new CardsOfSection(section, cards), HttpStatus.OK);
    }

    @PatchMapping("/cards/move/{movingCardId}")
    public ResponseEntity<Boolean> moveOne(@PathVariable Long movingCardId, @RequestBody MotionInfo motionInfo) {
        log.info("REQUEST move card[{}] to section {}, targetCardId : {} ",
                movingCardId, motionInfo.getSectionId(), motionInfo.getTargetCardId());
        CardResponse currentCard = cardService.findOne(movingCardId);
        boolean isMoved = cardService.move(motionInfo.getSectionId(), motionInfo.getTargetCardId(), movingCardId);

        LogRequest logRequest = LogRequest.moveRequest(currentCard, motionInfo);
        logService.save(logRequest);

        return new ResponseEntity<>(isMoved, HttpStatus.OK);
    }

}
