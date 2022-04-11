package team07.todolist.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import team07.todolist.domain.ActivityLog;
import team07.todolist.domain.Card;
import team07.todolist.dto.PatchCard;
import team07.todolist.dto.RequestCard;
import team07.todolist.dto.ResponseCard;
import team07.todolist.repository.ActivityLogRepository;
import team07.todolist.service.ActivityLogService;
import team07.todolist.service.CardService;

@Controller
@RequestMapping("/list")
public class CardController {

	private Logger log = LoggerFactory.getLogger(CardController.class);
	private final CardService cardService;
	private final ActivityLogService activityLogService;

	public CardController(CardService cardService, ActivityLogService activityLogService) {
		this.cardService = cardService;
		this.activityLogService = activityLogService;
	}

	@ResponseBody
	@GetMapping
	public List<ResponseCard> home() {
		return cardService.findAll();
	}

	@PostMapping
	public void save(@RequestBody RequestCard requestCard, HttpServletResponse response) {
		log.debug("{}", requestCard);
		cardService.save(requestCard);

		response.setStatus(HttpServletResponse.SC_OK);
	}

	@PostMapping("/move/horizon/{id}")
	public void dragAndDropHorizon(@PathVariable Long id, @RequestBody RequestCard requestCard, HttpServletResponse response) {

		cardService.dragAndDropHorizon(id, requestCard);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@PostMapping("/move/vertical/{id}")
	public void dragAndDropVertical(@PathVariable Long id, @RequestBody RequestCard requestCard, HttpServletResponse response) {

		cardService.dragAndDropVertical(id, requestCard);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@PatchMapping("/{id}")
	public void modifyCard(@PathVariable Long id, @RequestBody PatchCard patchCard, HttpServletResponse response) {

		cardService.changeText(id, patchCard);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		Card deletedCard = cardService.delete(id);
		log.debug("{}", deletedCard);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@ResponseBody
	@GetMapping("/menu")
	public List<ActivityLog> menu() {
		return activityLogService.findAll();
	}

	@GetMapping("/reset")
	public void reset(HttpServletResponse response) {
		cardService.reset();

		response.setStatus(HttpServletResponse.SC_OK);
	}
}
