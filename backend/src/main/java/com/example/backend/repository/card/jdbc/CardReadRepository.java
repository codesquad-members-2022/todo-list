package com.example.backend.repository.card.jdbc;

import com.example.backend.controller.card.dto.DoingItem;
import com.example.backend.controller.card.dto.HaveDoneItem;
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
        String query = "SELECT id, title, content, card_type, created_at, last_modified_at FROM cards WHERE card_type = ?";
        return jdbcTemplate.query(query, mapper, todo.name());
    }

    public List<HaveDoneItem> findHaveDoneItems(CardType done) {
        String query = "SELECT id, title, content, card_type, created_at, last_modified_at FROM cards WHERE card_type = ?";
        return jdbcTemplate.query(query, todoItem, done.name());
    }

    public List<DoingItem> findHaveDoingItems(CardType done) {
        String query = "SELECT id, title, content, card_type, created_at, last_modified_at FROM cards WHERE card_type = ?";
        return jdbcTemplate.query(query, todo, done.name());
    }

    public List<DoingItem> findDoingItems(CardType doing) {
        return null;
    }


    private static final RowMapper<TodoItem> mapper = (rs, rowNum) ->
            new TodoItem(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("card_type"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at"))

            );

    private static final RowMapper<HaveDoneItem> todoItem = (rs, rowNum) ->
            new HaveDoneItem(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("card_type"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at"))

            );

    private static final RowMapper<DoingItem> todo = (rs, rowNum) ->
            new DoingItem(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("card_type"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at"))

            );
}
