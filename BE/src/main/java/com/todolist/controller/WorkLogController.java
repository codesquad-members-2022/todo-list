package com.todolist.controller;

import com.todolist.dto.WorkLogListDto;
import com.todolist.service.WorkLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkLogController {

    private final WorkLogService workLogService;

    public WorkLogController(WorkLogService workLogService) {
        this.workLogService = workLogService;
    }

    @GetMapping("/user-logs")
    public WorkLogListDto getWorkLogList(@RequestParam("userId") String userId) {
        return workLogService.getWorkLogList(userId);
    }
}
