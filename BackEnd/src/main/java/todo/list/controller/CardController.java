package todo.list.controller;

import org.springframework.web.bind.annotation.*;
import todo.list.service.dto.*;
import todo.list.service.CardService;

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
}
