package com.team26.todolist.repository;

import com.team26.todolist.domain.Column;

public interface ColumnRepository {

    Column findById(Long id);
    Column updateOrder(Column column, Double order);
    Column updateTitle(Column column);
    boolean delete(Long id);
    Column save(Column column, Double order);
    Double getLastOrder();

}
