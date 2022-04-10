package com.example.backend.repository.column;

import com.example.backend.domain.column.Column;

import java.util.List;

public interface ColumnRepository {
    List<Column> findAll();
}
