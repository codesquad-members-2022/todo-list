package com.list.todo.repository;

import com.list.todo.domain.Task;

import java.util.Optional;

public interface TaskRepository {
    public Task add(Task task);
}
