package com.ijava.todolist.history.controller;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.controller.dto.HistoryResponse;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.repository.dto.JoinedHistory;
import com.ijava.todolist.history.service.HistoryService;
import com.ijava.todolist.history.util.HistoryResolver;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/history")
    public List<HistoryResponse> historyList() {
        return historyService.findAllJoinedHistory()
                .stream()
                .map(HistoryResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }
}
