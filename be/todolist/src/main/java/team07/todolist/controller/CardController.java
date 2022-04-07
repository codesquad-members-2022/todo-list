package team07.todolist.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team07.todolist.domain.Card;
import team07.todolist.dto.PatchCard;
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
		log.debug("{}", requestCard);
		cardService.save(requestCard);

		return "redirect:/list";
	}

	@PostMapping("/move/horizon/{id}")
	public String dragAndDropHorizon(@PathVariable Long id, @RequestBody RequestCard requestCard) {

		cardService.dragAndDropHorizon(id, requestCard);
		return "redirect:/list";
	}

	@PostMapping("/move/vertical/{id}")
	public String dragAndDropVertical(@PathVariable Long id, @RequestBody RequestCard requestCard) {

		cardService.dragAndDropVertical(id, requestCard);
		return "redirect:/list";
	}

	@PatchMapping("/{id}")
	public String modifyCard(@PathVariable Long id, @RequestBody PatchCard patchCard) {

		cardService.changeText(id, patchCard);
		return "redirect:/list";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		Card deletedCard = cardService.delete(id);
		log.debug("{}", deletedCard);
		return "redirect:/list";
	}
}
