package kr.codesquad.todolist.card;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class CardApiController {
    public static final String API_TODO_REDIRECT_URI = "/api/todo/card/%d";
    public static final String API_USER_KEY = "user";

    private final CardService cardService;

    @GetMapping
    public ResponseEntity readAll(@RequestHeader HttpHeaders headers) {
        String userId = headers.getFirst(API_USER_KEY);
        CardDto.CardsResponse response = cardService.readAllFrom(Long.parseLong(userId));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity write(@RequestBody @Valid CardDto.WriteRequest request) {
        CardDto.Redirection response = cardService.create(request);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(CardService.ERROR_MESSAGE
                    .apply(API_TODO_REDIRECT_URI, response.getCardId())))
                .build();
    }

    @GetMapping("/card/{id}")
    public ResponseEntity readOneToWrite(@PathVariable("id") Long cardId) {
        CardDto.CardResponse response = cardService.readFrom(cardId);
        return ResponseEntity.ok().body(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/card/{id}")
    public void edit(@PathVariable("id") Long cardId, @RequestBody @Valid CardDto.EditRequest request) {
        cardService.updateOf(cardId, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/card/{id}")
    public void remove(@PathVariable("id") Long cardId) {
        cardService.deleteFrom(cardId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/card/{id}/move")
    public void move(@PathVariable("id") Long cardId, @RequestBody @Valid CardDto.MoveRequest request) {
        Card.TodoStatus toStatus = Card.TodoStatus.from(request.getToStatus());
        Long toOrder = request.getToOrder();
        cardService.moveCardTo(cardId, toStatus, toOrder);
    }
}
