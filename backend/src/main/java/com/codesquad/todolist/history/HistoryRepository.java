package com.codesquad.todolist.history;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.util.KeyHolderFactory;
import com.codesquad.todolist.util.page.Criteria;

@Repository
public class HistoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final KeyHolderFactory keyHolderFactory;

    public HistoryRepository(NamedParameterJdbcTemplate jdbcTemplate,
        KeyHolderFactory keyHolderFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.keyHolderFactory = keyHolderFactory;
    }

    public History create(History history) {
        String sql = "insert into history (card_id, created_datetime, action) values (:cardId, :createdDateTime, :action)";

        KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

        MapSqlParameterSource source = new MapSqlParameterSource()
            .addValue("cardId", history.getCardId())
            .addValue("createdDateTime", history.getCreatedDateTime())
            .addValue("action", history.getAction().toString());

        jdbcTemplate.update(sql, source, keyHolder);

        if (keyHolder.getKey() != null) {
            history.setHistoryId(keyHolder.getKey().intValue());
        }
        return history;
    }

    public List<History> findAll(Criteria criteria) {
        String sql =
            "select history_id, user_name, column_name, title, action, history.created_datetime from history"
                + " join card on history.card_id = card.card_id"
                + " join `column` on card.column_id = `column`.column_id"
                + " join `user` on `column`.user_id = `user`.user_id"
                + " order by history_id desc"
                + " limit :limit offset :offset";

        MapSqlParameterSource source = new MapSqlParameterSource()
            .addValue("limit", criteria.getLimit())
            .addValue("offset", criteria.getOffset());

        return jdbcTemplate.query(sql, source, getRowMapper());
    }

    public Optional<History> findById(Integer historyId) {
        String sql =
            "select history_id, user_name, column_name, title, action, history.created_datetime from history"
                + " join card on history.card_id = card.card_id"
                + " join `column` on card.column_id = `column`.column_id"
                + " join `user` on `column`.user_id = `user`.user_id"
                + " where history.history_id = :historyId";

        MapSqlParameterSource source = new MapSqlParameterSource()
            .addValue("historyId", historyId);

        try {
            History history = jdbcTemplate.queryForObject(sql, source, getRowMapper());
            return Optional.ofNullable(history);

        } catch (DataAccessException e) {
            return Optional.empty();
        }

    }

    private RowMapper<History> getRowMapper() {
        return (rs, rowNum) -> new History(
            rs.getInt("history_id"),
            rs.getString("user_name"),
            rs.getString("column_name"),
            rs.getString("title"),
            Action.valueOf(rs.getString("action")),
            rs.getObject("created_datetime", LocalDateTime.class)
        );
    }
}
