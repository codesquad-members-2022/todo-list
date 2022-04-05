package todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import todolist.dto.CardsDto;
import todolist.service.CardService;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/todos")
    public CardsDto getCards() {
        return cardService.getCardList();
    }
}
