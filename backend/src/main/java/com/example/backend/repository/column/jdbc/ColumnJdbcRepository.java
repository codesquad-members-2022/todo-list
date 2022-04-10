package com.example.backend.repository.column.jdbc;

import com.example.backend.domain.column.Column;
import com.example.backend.repository.column.ColumnRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColumnJdbcRepository implements ColumnRepository {

    private final JdbcTemplate jdbcTemplate;

    public ColumnJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Column> findAll() {
        return null;
    }
}
