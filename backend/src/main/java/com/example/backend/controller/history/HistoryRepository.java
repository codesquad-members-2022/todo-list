package com.example.backend.controller.history;

import com.example.backend.domain.history.History;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class HistoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<History> findHistories(Long id) {
        String query = "SELECT author, title, content, created_at, action, last_modified_at FROM history WHERE id=:id";
        return null;
    }
}
