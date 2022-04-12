package kr.codesquad.todo.repository;

import kr.codesquad.todo.domain.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskRepository {
    public Task add(Task task);

    List<Task> getAll();
}
