package com.team05.todolist.controller;

import com.team05.todolist.domain.Event;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.service.CardService;
import com.team05.todolist.service.LogService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class CardController {

	private final CardService cardService;
	private final LogService logService;
	private final Logger logger = LoggerFactory.getLogger(CardController.class);

	public CardController(CardService cardService, LogService logService) {
		this.cardService = cardService;
		this.logService = logService;
	}

	@ApiOperation("카드 등록")
	@PostMapping("/cards")
	public ResponseEntity<LogDTO> create(CardDTO cardDto) {
		cardService.save(cardDto);
		LogDTO log = logService.save(Event.CREATE, cardDto.getTitle(), cardDto.getSection());

		logger.debug("[card-title] {}, [log-information] {}({})", cardDto.getTitle(), log.getLogEventType(), log.getLogTime()); // card Id 추가
		return ResponseEntity.ok().body(log);
	}

	@PutMapping("/cards/{id}")
	public ResponseEntity update(@PathVariable int id, CardDTO cardDto) {
		cardService.update(id, cardDto);

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@DeleteMapping("/cards/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		cardService.delete(id);

		return ResponseEntity.ok(HttpStatus.OK);
	}

}
