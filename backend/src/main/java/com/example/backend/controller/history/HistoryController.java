package com.example.backend.controller.history;

import com.example.backend.controller.ApiResult;
import com.example.backend.domain.history.History;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cards")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("{id}")
    public ApiResult<HistoryResponse> getCardHistory(@PathVariable("id") Long id){
        List<History> histories = historyService.findHistories(id);
        return null;
    }
}
