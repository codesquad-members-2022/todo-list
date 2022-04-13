package com.team26.todolist.controller;

import com.team26.todolist.dto.request.ColumnMoveRequest;
import com.team26.todolist.dto.request.ColumnRegistrationRequest;
import com.team26.todolist.dto.request.ColumnUpdateRequest;
import com.team26.todolist.dto.response.ColumnResponse;
import com.team26.todolist.service.ColumnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/columns")
public class ColumnController {

    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @PostMapping
    public ResponseEntity<ColumnResponse> createColumn(@RequestBody ColumnRegistrationRequest columnRegistrationRequest) {
        ColumnResponse savedColumn = columnService.addColumn(columnRegistrationRequest);


        return ResponseEntity.ok()
                .body(savedColumn);
    }

    @PutMapping
    public ResponseEntity<ColumnResponse> updateColumn(@RequestBody ColumnUpdateRequest columnUpdateRequest) {
        ColumnResponse updatedCard = columnService.modifyColumn(columnUpdateRequest);

        return ResponseEntity.ok()
                .body(updatedCard);
    }

    @PatchMapping
    public ResponseEntity<ColumnResponse> changeColumnOrder(@RequestBody ColumnMoveRequest columnMoveRequest) {
        ColumnResponse movedColumn = columnService.changeColumnOrder(columnMoveRequest);

        return ResponseEntity.ok()
                .body(movedColumn);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteColumn(@RequestBody Long id) {
        columnService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
