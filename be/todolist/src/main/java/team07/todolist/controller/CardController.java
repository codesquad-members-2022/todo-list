package team07.todolist.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team07.todolist.domain.Card;
import team07.todolist.dto.ResponseCard;
import team07.todolist.service.CardService;

@RestController
@RequestMapping("/list")
public class CardController {

	private CardService cardService;

	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@GetMapping
	public List<ResponseCard> home() {
//		ResponseEntity
		return null;
	}

	@PostMapping
	public String save(ResponseCard responseCard) {
		//todo
		cardService.save(responseCard);

		return "redirect: /list";
	}

	@PostMapping("/{row}")
	public ResponseCard post(@RequestParam int row) {

		Card card = cardService.changeRow(1, 1);

//		return new ResponseCard();
		return null;
	}

}
