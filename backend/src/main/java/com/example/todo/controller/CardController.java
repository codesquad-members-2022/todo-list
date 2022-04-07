package com.example.todo.controller;

import com.example.todo.controller.card.CardDto;
import com.example.todo.domain.Card;
import com.example.todo.service.CardService;
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
