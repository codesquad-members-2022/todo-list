package kr.codesquad.todolist.repository.log;

import kr.codesquad.todolist.domain.Log;

import java.util.List;

public interface LogRepository {

    boolean save(Log log);
    List<Log> findAll();
}
