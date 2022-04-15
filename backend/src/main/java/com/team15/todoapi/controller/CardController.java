package com.team15.todoapi.controller;

import com.team15.todoapi.controller.dto.DefaultResponse;
import com.team15.todoapi.controller.dto.card.CardRequest;
import com.team15.todoapi.controller.dto.card.CardResponse;
import com.team15.todoapi.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

	private final CardService cardService;

	@GetMapping
	public List<CardResponse.ListInfo> retrieveList(@RequestParam String userId) {
		return cardService.findAll(userId);
	}

	@PostMapping
	public ResponseEntity add(@RequestBody @Validated CardRequest cardRequest,
		BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.info("Card ADD - 검증 오류 발생! error = {} ", bindingResult);
			return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		DefaultResponse response = cardService.add(cardRequest);
		CardResponse.AddInfo cardResponse = (CardResponse.AddInfo) response.getCustomResponse();

		return new ResponseEntity(cardResponse, response.getHttpStatus());
	}

	@PatchMapping
	public ResponseEntity update(@RequestBody @Validated CardRequest cardRequest,
		BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.info("Card UPDATE - 검증 오류 발생! error = {} ", bindingResult);
			return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		DefaultResponse response = cardService.update(cardRequest);
		CardResponse.UpdateInfo cardResponse = (CardResponse.UpdateInfo) response.getCustomResponse();

		return new ResponseEntity(cardResponse, response.getHttpStatus());
	}
}
