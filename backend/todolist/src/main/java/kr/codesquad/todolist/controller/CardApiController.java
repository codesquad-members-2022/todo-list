package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.controller.request.CardRequestDto;
import kr.codesquad.todolist.controller.response.CardResponseDto;
import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.service.CardService;
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
        CardResponseDto.CardsResponse response = cardService.readAllFrom(Long.parseLong(userId));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity write(@RequestBody @Valid CardRequestDto.WriteRequest request) {
        CardResponseDto.Redirection response = cardService.create(request);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(CardService.ERROR_MESSAGE
                    .apply(API_TODO_REDIRECT_URI, response.getCardId())))
                .build();
    }

    @GetMapping("/card/{id}")
    public ResponseEntity readOneToWrite(@PathVariable("id") Long cardId) {
        CardResponseDto.CardResponse response = cardService.readFrom(cardId);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/card/{id}")
    public void edit(@PathVariable("id") Long cardId, @RequestBody @Valid CardRequestDto.EditRequest request) {
        cardService.updateOf(cardId, request);
    }

    @DeleteMapping("/card/{id}")
    public void remove(@PathVariable("id") Long cardId) {
        cardService.deleteFrom(cardId);
    }

    @PatchMapping("/card/{id}/move")
    public void move(@PathVariable("id") Long cardId, @RequestBody @Valid CardRequestDto.MoveRequest request) {
        Card.TodoStatus toStatus = Card.TodoStatus.from(request.getToStatus());
        Long toOrder = request.getToOrder();
        cardService.moveCardTo(cardId, toStatus, toOrder);
    }
}
