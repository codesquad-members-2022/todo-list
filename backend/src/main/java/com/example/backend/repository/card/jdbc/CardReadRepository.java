package com.example.backend.repository.card.jdbc;

import com.example.backend.controller.card.dto.CompletedItem;
import com.example.backend.controller.card.dto.ProgressingItem;
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

    public List<TodoItem> findItemsTodoItems(CardType todo) {
        String query = "SELECT id, title, content, created_at, last_modified_at FROM cards WHERE card_type = ?";
        return jdbcTemplate.query(query, mapper, todo.name());
    }

    public List<ProgressingItem> findHaveDoneItems(CardType done) {
        String query = "SELECT id, title, content, created_at, last_modified_at FROM cards WHERE card_type = ?";
        return jdbcTemplate.query(query, todoItem, done.name());
    }

    public List<CompletedItem> findHaveDoingItems(CardType done) {
        String query = "SELECT id, title, content, created_at, last_modified_at FROM cards WHERE card_type = ?";
        return jdbcTemplate.query(query, todo, done.name());
    }

    public List<CompletedItem> findDoingItems(CardType doing) {
        return null;
    }


    private static final RowMapper<TodoItem> mapper = (rs, rowNum) ->
            new TodoItem(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at"))
            );

    private static final RowMapper<ProgressingItem> todoItem = (rs, rowNum) ->
            new ProgressingItem(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at"))

            );

    private static final RowMapper<CompletedItem> todo = (rs, rowNum) ->
            new CompletedItem(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at"))

            );
}
