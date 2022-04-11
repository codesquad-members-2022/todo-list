package kr.codesquad.todo.repository;

import kr.codesquad.todo.domain.Task;

public interface TaskRepository {
    public Task add(Task task);
}
