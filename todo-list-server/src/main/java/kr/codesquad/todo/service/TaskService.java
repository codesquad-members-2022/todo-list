package kr.codesquad.todo.service;

import kr.codesquad.todo.domain.Task;
import kr.codesquad.todo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<Task> createTask(Task task) {
        if (isRequiredNull(task)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            taskRepository.add(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    private boolean isRequiredNull (Task task) {
        return Objects.isNull(task.getTitle()) || Objects.isNull(task.getContent()) || Objects.isNull(task.getAuthor()) || Objects.isNull(task.getStatus());
    }

    public ResponseEntity<Map<Integer, List<Task>>> getAllTasks() {
        List<Task> statusOne = new ArrayList<>();
        List<Task> statusTwo = new ArrayList<>();
        List<Task> statusThree = new ArrayList<>();
        List<Task> tasks;

        try {
            tasks = taskRepository.getAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        for (Task task : tasks) {
            switch (task.getStatus()) {
                case 1:
                    statusOne.add(task);
                    break;
                case 2:
                    statusTwo.add(task);
                    break;
                case 3:
                    statusThree.add(task);
                    break;
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        Map<Integer, List<Task>> taskMap = new HashMap<>();
        taskMap.put(1, statusOne);
        taskMap.put(2, statusTwo);
        taskMap.put(3, statusThree);

        return ResponseEntity.status(HttpStatus.OK).body(taskMap);
    }
}
