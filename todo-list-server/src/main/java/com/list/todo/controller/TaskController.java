package com.list.todo.controller;

import com.list.todo.domain.Task;
import com.list.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public ResponseEntity<Object> add(Task task) {
        ResponseEntity<Object> result = null;

        Task createdTask = taskService.createTask(task);

        if (Objects.isNull(createdTask)) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return result;
    }
}
