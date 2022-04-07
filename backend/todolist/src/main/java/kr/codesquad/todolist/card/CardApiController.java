package kr.codesquad.todolist.card;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/todo")
public class CardApiController {
	@PostMapping()
	public ResponseEntity write(@RequestBody @Valid CardDto.WriteRequest request) {
		CardDto.WriteResponse response = CardService.createCard(request);
		return ResponseEntity.ok().body(response);
	}
}
