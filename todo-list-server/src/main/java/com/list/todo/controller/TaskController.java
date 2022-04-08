package com.list.todo.controller;

import com.list.todo.domain.Task;
import com.list.todo.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@RestController
@Api(value = "TaskController v1")
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "추가", notes = "일 목록에 태스크를 추가합니다.\n ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청이 성공했습니다.", response = Task.class),
            @ApiResponse(code = 400, message = "잘못 된 파라미터 요청 입니다."),
            @ApiResponse(code = 500, message = "서버에서 발생한 에러입니다.")
    })
    @PostMapping("/task")
    public ResponseEntity<Object> add(@RequestBody Task task) {
        try {
            taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (DataIntegrityViolationException j) {
            j.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
