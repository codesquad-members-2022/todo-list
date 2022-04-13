package kr.codesquad.todolist.repository;

import kr.codesquad.todolist.domain.Log;

import java.util.List;

public interface LogRepository {

    boolean save(Log log);
    List<Log> findAll();
}
