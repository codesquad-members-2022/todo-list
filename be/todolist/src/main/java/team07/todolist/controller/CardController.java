package team07.todolist.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team07.todolist.domain.Card;
import team07.todolist.dto.RequestCard;
import team07.todolist.dto.ResponseCard;
import team07.todolist.service.CardService;

@Controller
@RequestMapping("/list")
public class CardController {

	private Logger log = LoggerFactory.getLogger(CardController.class);
	private final CardService cardService;

	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@ResponseBody
	@GetMapping
	public List<ResponseCard> home() {
		return cardService.findAll();
	}

	@PostMapping
	public String save(@RequestBody RequestCard requestCard) {
		log.debug("{}", requestCard.toString());
		cardService.save(requestCard);

		return "redirect:/list";
	}

	@PostMapping("/{id}")
	public RequestCard update(@RequestParam Long id, @RequestBody RequestCard requestCard) {

		Card card = cardService.changeRow(id, requestCard);

//		return new ResponseCard();
		return null;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		Card deletedCard = cardService.delete(id);
		log.debug("{}", deletedCard);
		return "redirect:/list";
	}
}
