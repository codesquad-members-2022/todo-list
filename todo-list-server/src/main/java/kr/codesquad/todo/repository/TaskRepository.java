package kr.codesquad.todo.repository;

import kr.codesquad.todo.domain.Task;

import java.util.List;

public interface TaskRepository {
    Task add(Task task);

    List<Task> getAll();

    Task changeStatus(long idx, int status);
}
