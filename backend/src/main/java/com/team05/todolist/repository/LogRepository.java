package com.team05.todolist.repository;

import com.team05.todolist.domain.Log;
import java.util.List;

public interface LogRepository {

    int save(Log log);
    List<Log> findAll();

}
