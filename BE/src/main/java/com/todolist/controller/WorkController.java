package com.todolist.controller;

import com.todolist.dto.ColumnList;
import com.todolist.service.WorkService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/works")
    public ColumnList getWorkList() {
        return workService.getColumnList();
    }
}
