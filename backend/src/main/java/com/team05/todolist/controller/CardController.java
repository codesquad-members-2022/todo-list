package com.team05.todolist.controller;

import com.team05.todolist.domain.Event;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.service.CardService;
import com.team05.todolist.service.LogService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
}
