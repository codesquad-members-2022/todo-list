package kr.codesquad.todolist.card;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class CardApiController {
	private final CardService cardService;

	@PostMapping
	public ResponseEntity write(@RequestBody @Valid CardDto.WriteRequest request) {
		CardDto.RedirectInfo response = cardService.createCard(request);
		return ResponseEntity.status(HttpStatus.FOUND)
			.location(URI.create(getRedirectUri(response)))
			.build();
	}

	@GetMapping("/{user-id}/card/{id}")
	public ResponseEntity readOneToWrite(@PathVariable("user-id") Long userId, @PathVariable Long id) {
		CardDto.WriteResponse response = cardService.readOf(id, userId);
		return ResponseEntity.ok().body(response);
	}

	private String getRedirectUri(CardDto.RedirectInfo response) {
		return String.format("/api/todo/%d/card/%d", response.getUserId(), response.getTodoId());
	}
}
