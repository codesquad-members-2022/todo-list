package com.codesquad.todolist.history;

import com.codesquad.todolist.util.KeyHolderFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
        KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

        jdbcTemplate.update(
            "insert into history (card_id, created_date, field, old_value, new_value, action) values (:cardId, :createdDate, :field, :oldValue, :new_value, :action)",
            new BeanPropertySqlParameterSource(history), keyHolder);

        if (keyHolder.getKey() != null) {
            history.setHistoryId(keyHolder.getKey().intValue());
        }
        return history;
    }

    public void createAll(List<History> histories) {
        jdbcTemplate.batchUpdate(
            "insert into history (card_id, created_date, field, old_value, new_value, action) values (:cardId, :createdDate, :field, :oldValue, :new_value, :action)",
            SqlParameterSourceUtils.createBatch(histories)
        );
    }
}
