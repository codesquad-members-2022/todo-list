package com.team05.todolist.repository;

import com.team05.todolist.domain.Log;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcLogRepository implements LogRepository {

    @Override
    public void save(Log log) {

    }

    @Override
    public List<Log> findAll() {
        return null;
    }
}
