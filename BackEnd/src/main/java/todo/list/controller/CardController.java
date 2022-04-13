package todo.list.controller;

import org.springframework.web.bind.annotation.*;
import todo.list.service.dto.CardModifyRequest;
import todo.list.service.dto.CardCollectionResponse;
import todo.list.service.dto.CardSaveRequest;
import todo.list.service.CardService;
import todo.list.service.dto.CommandResultResponse;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public CommandResultResponse save(@RequestBody CardSaveRequest cardSaveRequest) {
        return cardService.save(cardSaveRequest);
    }

    @GetMapping
    public CardCollectionResponse getAllCards() {
        return cardService.findCollections();
    }

    @PatchMapping("/{cardId}")
    public CommandResultResponse modifyCard(@RequestBody CardModifyRequest cardModifyRequest) {
        return cardService.modify(cardModifyRequest);
    }
}
