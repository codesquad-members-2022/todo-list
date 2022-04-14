package com.team15.todoapi.controller;

import com.team15.todoapi.controller.history.HistoryResponse;
import com.team15.todoapi.domain.History;
import com.team15.todoapi.service.HistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/histories")
@RequiredArgsConstructor
public class HistoryController {

	private final HistoryService historyService;

	@GetMapping
	public List<HistoryResponse> histories(String userId){
		return historyService.findAll(userId);
	}
}
