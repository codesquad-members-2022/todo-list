package com.team05.todolist.controller;

import com.team05.todolist.domain.Event;
import com.team05.todolist.domain.Section;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.ClassifiedCardsDTO;
import com.team05.todolist.domain.dto.ListResponseDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.domain.dto.ResponseDTO;
import com.team05.todolist.service.CardService;
import com.team05.todolist.service.LogService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<ResponseDTO> create(CardDTO cardDto) {
		CardDTO newCardDto = cardService.save(cardDto);
		LogDTO log = logService.save(Event.CREATE, cardDto.getTitle(), null, cardDto.getSection());

		logger.debug("[card-{}] {}, [log-information] {}-{}({})", newCardDto.getCardId(), newCardDto.getTitle(), log.getSection(), log.getLogEventType(), log.getLogTime());
		return ResponseEntity.ok().body(new ResponseDTO(newCardDto, log));
	}

	@ApiOperation("카드 수정")
	@PutMapping("/cards/{id}")
	public ResponseEntity<ResponseDTO> update(@PathVariable int id, CardDTO cardDto) {
		CardDTO updatedCardDto = cardService.update(id, cardDto);
		LogDTO log = logService.save(Event.UPDATE, cardDto.getTitle(), null, cardDto.getSection());

		return ResponseEntity.ok().body(new ResponseDTO(updatedCardDto, log));
	}

	@ApiOperation("카드 이동")
	@PutMapping("/cards/{id}/move")
	public ResponseEntity<ResponseDTO> move(@PathVariable int id, CardDTO cardDto) {
		CardDTO movedCardDto = cardService.update(id, cardDto);
		LogDTO log = logService.save(Event.MOVE, cardDto.getTitle(), cardDto.getPrevSection(), cardDto.getSection());

		return ResponseEntity.ok().body(new ResponseDTO(movedCardDto, log));
	}

	@ApiOperation("카드 삭제")
	@DeleteMapping("/cards/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {
		CardDTO deletedCardDto = cardService.delete(id);
		LogDTO log = logService.delete(Event.DELETE, deletedCardDto.getTitle(), deletedCardDto.getPrevSection(), deletedCardDto.getSection());
		return ResponseEntity.ok().body(new ResponseDTO(deletedCardDto, log));
	}

}
