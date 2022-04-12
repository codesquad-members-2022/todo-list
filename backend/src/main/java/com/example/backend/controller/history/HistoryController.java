package com.example.backend.controller.history;

import com.example.backend.controller.ApiResult;
import com.example.backend.domain.history.History;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.controller.ApiResult.OK;

@RestController
@RequestMapping("api/histories")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("")
    public ApiResult<History> saveHistory(@RequestBody HistorySaveRequest request) {
        return OK(historyService.saveHistory(request));
    }

    @GetMapping("members/{id}/cards/{id}")
    public ApiResult<HistoryResponse> getCardHistory(@PathVariable("memberId") Long memberId, @PathVariable("cardId") Long cardId) {
        List<History> histories = historyService.findHistories(memberId, cardId);
        return null;
    }
}
