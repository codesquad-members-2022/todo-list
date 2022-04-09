package com.team05.todolist.controller;

import com.team05.todolist.domain.Card;
import com.team05.todolist.domain.Event;
import com.team05.todolist.domain.Log;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.domain.dto.ResponseDTO;
import com.team05.todolist.service.CardService;
import com.team05.todolist.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class CardController {

	private final CardService cardService;
	private final LogService logService;
	private Logger logger = LoggerFactory.getLogger(CardController.class);

	public CardController(CardService cardService, LogService logService) {
		this.cardService = cardService;
		this.logService = logService;
	}

	@PostMapping("/cards")
	public ResponseEntity<LogDTO> create(CardDTO cardDto) {
		cardService.save(cardDto);
		LogDTO log = logService.save(Event.CREATE, cardDto);

		logger.debug("[card-title] {}, [log-information] {}({})", cardDto.getTitle(), log.getLogEventType(), log.getLogTime());
		return ResponseEntity.ok().body(log);
	}
}
