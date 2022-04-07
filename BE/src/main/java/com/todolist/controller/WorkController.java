package com.todolist.controller;

import com.todolist.dto.ColumnListDto;
import com.todolist.dto.WorkLogListDto;
import com.todolist.service.WorkLogService;
import com.todolist.service.WorkService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkController {

    private final WorkService workService;
    private final WorkLogService workLogService;

    public WorkController(WorkService workService, WorkLogService workLogService) {
        this.workService = workService;
        this.workLogService = workLogService;
    }

    @GetMapping("/works")
    public ColumnListDto getWorkList(@RequestParam("userId") String userId) {
        return workService.getColumnList(userId);
    }

    @GetMapping("/work-logs")
    public WorkLogListDto getWorkLogList(@RequestParam("userId") String userId) {
        return workLogService.getWorkLogList(userId);
    }
}
