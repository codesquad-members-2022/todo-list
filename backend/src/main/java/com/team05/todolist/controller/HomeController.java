package com.team05.todolist.controller;

import com.team05.todolist.domain.dto.ClassifiedCardsDTO;
import com.team05.todolist.domain.dto.ListResponseDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.service.CardService;
import com.team05.todolist.service.LogService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private final CardService cardService;
	private final LogService logService;
	private final Logger logger = LoggerFactory.getLogger(CardController.class);

	public HomeController(CardService cardService, LogService logService) {
		this.cardService = cardService;
		this.logService = logService;
	}

	@ApiOperation("전체 카드 조회")
	@GetMapping("/api/cards")
	public ResponseEntity<ListResponseDTO> inquireAll() {
		ClassifiedCardsDTO classifiedCards = cardService.findCards();
		List<LogDTO> logs = logService.findLogs();
		return ResponseEntity.ok().body(new ListResponseDTO(classifiedCards, logs));
	}
}
