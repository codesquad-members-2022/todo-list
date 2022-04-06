package todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todolist.dto.CardDto;
import todolist.service.CardService;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/todo")
    public CardDto add(@RequestBody CardDto cardDto) {
        return cardService.addCard(cardDto);
    }
}
