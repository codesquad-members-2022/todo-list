package kr.codesquad.todolist.card;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class CardApiController {
	public static final String API_TODO_REDIRECT_URI = "/api/todo/card/%d";

	private final CardService cardService;

	@GetMapping
	public ResponseEntity welcomePage(@RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("user");
		CardDto.CardsResponse response = cardService.readAllFrom(Long.parseLong(userId));
		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity write(@RequestBody @Valid CardDto.WriteRequest request) {
		CardDto.Redirection response = cardService.create(request);
		return ResponseEntity.status(HttpStatus.FOUND)
			.location(URI.create(getRedirectUri(response)))
			.build();
	}

	@GetMapping("/card/{id}")
	public ResponseEntity readOneToWrite(@PathVariable("id") Long cardId) {
		CardDto.CardResponse response = cardService.readOf(cardId);
		return ResponseEntity.ok().body(response);
	}

	private String getRedirectUri(CardDto.Redirection response) {
		return String.format(API_TODO_REDIRECT_URI, response.getCardId());
	}
}
