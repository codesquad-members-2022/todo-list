package com.codesquad.todolist.column;

import com.codesquad.todolist.util.KeyHolderFactory;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
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
        String sql = "insert into `column` (user_id, column_name) values (:userId, :columnName)";

        KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(column), keyHolder);

        if (keyHolder.getKey() != null) {
            column.setColumnId(keyHolder.getKey().intValue());
        }
        return column;
    }

    public List<Column> findAll() {
        String sql = "select column_id, user_id, column_name from `column` where deleted = false";
        return jdbcTemplate.query(sql, getColumnRowMapper());
    }

    private RowMapper<Column> getColumnRowMapper() {
        return (rs, count) -> new Column(
            rs.getInt("column_id"),
            rs.getInt("user_id"),
            rs.getString("column_name")
        );
    }

}
