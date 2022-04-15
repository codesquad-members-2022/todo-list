package com.team26.todolist.repository;

import com.team26.todolist.domain.History;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository {

    List<History> findAll();

    History findById(Long id);

    History save(History history);
}
