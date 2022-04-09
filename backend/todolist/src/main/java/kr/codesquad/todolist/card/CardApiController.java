package kr.codesquad.todolist.card;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class CardApiController {
	private final CardService cardService;

	@PostMapping
	public ResponseEntity write(@RequestBody @Valid CardDto.WriteRequest request) {
		CardDto.WriteResponse response = cardService.createCard(request);
		return ResponseEntity.ok().body(response);
	}
}
