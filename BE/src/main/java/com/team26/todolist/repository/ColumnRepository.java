package com.team26.todolist.repository;

import com.team26.todolist.domain.Column;
import java.util.Arrays;
import java.util.List;

public interface ColumnRepository {

    List<Column> findAll();
    Column findById(Long id);
    Column updateOrder(Column column, Double order);
    Column updateTitle(Column column);
    boolean delete(Long id);
    Column saveNewColumn(Column column);

}
