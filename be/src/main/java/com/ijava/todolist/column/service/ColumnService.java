package com.ijava.todolist.column.service;

import com.ijava.todolist.column.domain.Column;
import com.ijava.todolist.column.repository.ColumnRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ColumnService {

    private final ColumnRepository columnRepository;

    public ColumnService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public List<Column> findColumns() {
        return columnRepository.findAll()
            .orElseGet(Collections::emptyList);
    }
}
