package com.codesquad.todolist.column;

import com.codesquad.todolist.util.KeyHolderFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ColumnRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final KeyHolderFactory keyHolderFactory;

    public ColumnRepository(NamedParameterJdbcTemplate jdbcTemplate,
        KeyHolderFactory keyHolderFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.keyHolderFactory = keyHolderFactory;
    }

    public Column create(Column column) {
        KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

        jdbcTemplate.update(
            "insert into `column` (user_id, column_name) values (:userId, :columnName)",
            new BeanPropertySqlParameterSource(column), keyHolder);

        if (keyHolder.getKey() != null) {
            column.setColumnId(keyHolder.getKey().intValue());
        }
        return column;
    }

}
