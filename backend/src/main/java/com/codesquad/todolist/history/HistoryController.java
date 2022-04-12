package com.codesquad.todolist.history;

import com.codesquad.todolist.history.dto.HistoryResponse;
import com.codesquad.todolist.util.page.Criteria;
import com.codesquad.todolist.util.page.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/histories")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public Slice<HistoryResponse> findAll(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return historyService.findAll(new Criteria(page, size));
    }
}
