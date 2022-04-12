package com.todolist.controller;

import com.todolist.dto.ColumnListDto;
import com.todolist.dto.WorkDto;
import com.todolist.dto.WorkRequestFormDto;
import com.todolist.service.WorkService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/works")
    public ColumnListDto getWorkList(@RequestParam("userId") String userId) {
        return workService.getColumnList(userId);
    }

    @PostMapping("/works")
    public WorkDto create(@RequestBody WorkRequestFormDto workRequestFormDto) {
        return workService.create(workRequestFormDto);
    }
}
