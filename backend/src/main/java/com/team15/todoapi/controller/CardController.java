package com.team15.todoapi.controller;

import com.team15.todoapi.domain.Card;
import com.team15.todoapi.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

	private final CardService cardService;

	@GetMapping("")
	public List<Card> cards(){
		return cardService.findAll();
	}
}
