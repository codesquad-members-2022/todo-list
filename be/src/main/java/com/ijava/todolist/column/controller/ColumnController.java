package com.ijava.todolist.column.controller;

import com.ijava.todolist.card.service.CardService;
import com.ijava.todolist.column.controller.dto.ColumnResponse;
import com.ijava.todolist.column.domain.Column;
import com.ijava.todolist.column.service.ColumnService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ColumnController {

    private final ColumnService columnService;
    private final CardService cardService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/columns")
    public List<ColumnResponse> columnList() {
        List<Column> columnList = columnService.findColumns();
        return columnList.stream()
            .map(column -> ColumnResponse.from(column, cardService))
            .collect(Collectors.toList());
    }
}
