package com.example.backend.controller.history;

import static com.example.backend.controller.ApiResult.OK;

import com.example.backend.controller.ApiResult;
import com.example.backend.controller.history.dto.HistorySaveRequest;
import com.example.backend.domain.history.History;
import com.example.backend.service.history.HistoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api/histories")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("")
    public ApiResult<History> saveHistory(@Valid @RequestBody HistorySaveRequest request) {
        return OK(historyService.saveHistory(request));
    }

    @GetMapping("members/{memberId}/cards/{memberId}")
    public ApiResult<List<HistoryResponse>> getCardHistory(@PathVariable("memberId") Long memberId, @PathVariable("memberId") Long cardId) {
        return OK(historyService.findHistories(memberId, cardId));
    }
}
