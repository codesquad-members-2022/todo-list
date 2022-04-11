package com.ijava.todolist.history.repository;

import com.ijava.todolist.history.domain.History;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository {

    void save(History history);

    Optional<List<History>> findAll();
}
