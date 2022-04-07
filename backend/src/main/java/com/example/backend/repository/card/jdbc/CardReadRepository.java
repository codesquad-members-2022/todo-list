package com.example.backend.repository.card.jdbc;

import com.example.backend.controller.card.dto.CompletedItem;
import com.example.backend.controller.card.dto.ProgressingItem;
import com.example.backend.controller.card.dto.Task;
import com.example.backend.controller.card.dto.TodoItem;
import com.example.backend.domain.card.CardType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.backend.utils.TimeUtils.dateTimeOf;

@Repository
public class CardReadRepository {

    private final JdbcTemplate jdbcTemplate;

    public CardReadRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> findItemsByCardType(CardType todo) {
        String query = "SELECT id, title, card_type, content, created_at, last_modified_at FROM cards WHERE card_type = ?";
        return jdbcTemplate.query(query, mapper, todo.name());
    }

    private static final RowMapper<Task> mapper = (rs, rowNum) ->
            new Task(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("card_type"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at"))
            );

}
