package todo.list.controller;

import org.springframework.web.bind.annotation.*;
import todo.list.service.dto.CardModifyDto;
import todo.list.service.dto.CardResponse;
import todo.list.service.dto.CardSaveDto;
import todo.list.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public void save(@RequestBody CardSaveDto cardSaveDto) {
        cardService.save(cardSaveDto);
    }

    @GetMapping
    public CardResponse getAllCards() {
        return cardService.findCollections();
    }

    @PatchMapping("/{cardId}")
    public void modifyCard(@RequestBody CardModifyDto cardModifyDto) {
        cardService.modify(cardModifyDto);
    }
}
