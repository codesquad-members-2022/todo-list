package com.codesquad.todolist.history;

import com.codesquad.todolist.history.dto.HistoryResponse;
import com.codesquad.todolist.util.page.Criteria;
import com.codesquad.todolist.util.page.Slice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "History API")
@RestController
@RequestMapping("/histories")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @ApiOperation(value = "히스토리 조회", notes = "애플리케이션 내 액션에 따른 히스토리를 조회합니다.")
    @GetMapping
    public Slice<HistoryResponse> findAll(
        @RequestParam(value = "length", defaultValue = "0") Integer length,
        @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return historyService.findAll(new Criteria(length, size));
    }
}
