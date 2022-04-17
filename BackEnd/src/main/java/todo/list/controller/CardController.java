package todo.list.controller;

import org.springframework.web.bind.annotation.*;
import todo.list.service.CardService;
import todo.list.service.dto.*;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public CardCommandResponse save(@RequestBody CardSaveRequest cardSaveRequest) {
        return cardService.save(cardSaveRequest);
    }

    @GetMapping
    public CardCollectionResponse getAllCards() {
        return cardService.findCollections();
    }

    @PatchMapping("/{cardId}")
    public CardCommandResponse modifyCard(@RequestBody CardModifyRequest cardModifyRequest) {
        return cardService.modify(cardModifyRequest);
    }

    @DeleteMapping("/{cardId}")
    public CardCommandResponse delete(@PathVariable Long cardId) {
        return cardService.delete(cardId);
    }

    @PatchMapping("/{cardId}/move")
    public CardCommandResponse move(@PathVariable Long cardId, @RequestBody CardMoveRequest cardMoveRequest) {
        return cardService.move(cardId, cardMoveRequest);
    }
}
