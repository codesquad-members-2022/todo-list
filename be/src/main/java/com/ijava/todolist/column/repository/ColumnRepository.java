package com.ijava.todolist.column.repository;

import com.ijava.todolist.column.domain.Column;
import java.util.List;
import java.util.Optional;

public interface ColumnRepository {

    Optional<List<Column>> findAll();
}
