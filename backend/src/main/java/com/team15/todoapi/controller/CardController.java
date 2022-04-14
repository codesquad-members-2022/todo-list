package com.team15.todoapi.controller;

import com.team15.todoapi.controller.dto.card.CardRequest;
import com.team15.todoapi.controller.dto.card.CardResponse;
import com.team15.todoapi.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<CardResponse.ListInfo> retrieveList(@RequestParam String userId){
		return cardService.findAll(userId);
	}

	@PostMapping
	public ResponseEntity add(@RequestBody CardRequest cardRequest){
		CardResponse.AddInfo cardResponse = cardService.add(cardRequest);

		return new ResponseEntity(cardResponse, HttpStatus.CREATED);
	}
}
