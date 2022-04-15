package com.todolist.controller;

import com.todolist.dto.UserLogListDto;
import com.todolist.service.UserLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLogController {

    private final UserLogService userLogService;

    public UserLogController(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @GetMapping("/user-logs")
    public UserLogListDto getWorkLogList(@RequestParam("userId") String userId) {
        return userLogService.getWorkLogList(userId);
    }
}
