package com.team26.todolist.repository;

import com.team26.todolist.domain.Column;
import java.util.Arrays;
import java.util.List;

public interface ColumnRepository {

    List<Column> findAll();
    Column findById(Long id);
    Column updateOrder(Column column);
    Column updateTitle(Column column);
    void delete(Long id);
    Column saveNewColumn(Column column);

}
