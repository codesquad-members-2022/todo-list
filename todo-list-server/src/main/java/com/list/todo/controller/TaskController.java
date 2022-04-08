package com.list.todo.controller;

import com.list.todo.domain.Task;
import com.list.todo.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Api(value = "TaskController v1")

public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "추가", notes = "할일 목록에 태스크를 추가합니다.\n ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청이 성공했습니다."),
            @ApiResponse(code = 400, message = "접근이 올바르지 않습니다."),

    })
    @PostMapping("/task")
    public ResponseEntity<Object> add(@RequestBody Task task) {
        ResponseEntity<Object> result = null;

        Task createdTask = taskService.createTask(task);

        if (Objects.isNull(createdTask)) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(task);
        }
        return result;
    }
}
