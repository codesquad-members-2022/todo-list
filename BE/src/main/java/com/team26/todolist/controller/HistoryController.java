package com.team26.todolist.controller;

import com.team26.todolist.dto.response.HistoryResponse;
import com.team26.todolist.service.HistoryService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/histories")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getHistories() {

        List<HistoryResponse> histories = historyService.findHistories();
        return ResponseEntity.ok().body(histories);
    }

}
