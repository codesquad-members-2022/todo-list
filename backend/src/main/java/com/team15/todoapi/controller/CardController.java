package com.team15.todoapi.controller;

import com.team15.todoapi.controller.card.CardResponse;
import com.team15.todoapi.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

	private final CardService cardService;

	@GetMapping("")
	public List<CardResponse> retrieveList(){
		return cardService.findAll();
	}


}
