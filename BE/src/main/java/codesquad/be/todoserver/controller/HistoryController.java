package codesquad.be.todoserver.controller;

import codesquad.be.todoserver.domain.History;
import codesquad.be.todoserver.service.HistoryService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HistoryController {

	private final HistoryService historiesService;

	public HistoryController(HistoryService historiesService) {
		this.historiesService = historiesService;
	}

	@GetMapping("/histories")
	public List<History> getAllHistory() {
		return historiesService.getAllHistory();
	}
}
