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
    public CommandResultResponse save(@RequestBody CardSaveRequest cardSaveRequest) {
        CardCommandResponse cardCommandResponse = cardService.save(cardSaveRequest);
        return new CommandResultResponse(201, cardCommandResponse);
    }

    @GetMapping
    public CardCollectionResponse getAllCards() {
        return cardService.findCollections();
    }

    @PatchMapping("/{cardId}")
    public CommandResultResponse modifyCard(@RequestBody CardModifyRequest cardModifyRequest) {
        CardCommandResponse cardCommandResponse = cardService.modify(cardModifyRequest);
        return new CommandResultResponse(200, cardCommandResponse);
    }

    @DeleteMapping("/{cardId}")
    public CommandResultResponse delete(@PathVariable Long cardId) {
        CardCommandResponse cardCommandResponse = cardService.delete(cardId);
        return new CommandResultResponse(200, cardCommandResponse);
    }

    @PatchMapping("/{cardId}/move")
    public CommandResultResponse move(@PathVariable Long cardId, @RequestBody CardMoveRequest cardMoveRequest) {
        CardCommandResponse cardCommandResponse = cardService.move(cardId, cardMoveRequest);
        return new CommandResultResponse(200, cardCommandResponse);
    }
}
