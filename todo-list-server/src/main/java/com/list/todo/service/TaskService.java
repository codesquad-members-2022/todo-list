package com.list.todo.service;

import com.list.todo.domain.Task;
import com.list.todo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<Object> createTask(Task task) {
        if (isRequiredNull(task)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            taskRepository.add(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private boolean isRequiredNull (Task task) {
        return Objects.isNull(task.getTitle()) || Objects.isNull(task.getContent()) || Objects.isNull(task.getAuthorNickname()) || Objects.isNull(task.getStatus());
    }
}
