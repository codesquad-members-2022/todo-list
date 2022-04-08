package com.team05.todolist.controller;

import com.team05.todolist.domain.Card;
import com.team05.todolist.domain.Event;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.domain.dto.ResponseDTO;
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
	public ResponseEntity<ResponseDTO> create(CardDTO cardDto) {
		CardDTO card = cardService.save(cardDto);
		LogDTO log = logService.save(Event.CREATE, cardDto);
		ResponseDTO responseDTO = new ResponseDTO(card, log);
		logger.debug("card-{}: {}, log: {}({})", card.getCardId(), card.getTitle(),
			log.getLogEvent(), log.getLogTime());
		return ResponseEntity.ok().body(responseDTO);
	}
}
