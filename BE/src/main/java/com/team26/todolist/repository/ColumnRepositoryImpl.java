package com.team26.todolist.repository;

import com.team26.todolist.domain.Column;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ColumnRepositoryImpl implements ColumnRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Column> columnRowMapper = columnRowMapper();
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public ColumnRepositoryImpl(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Column> findAll() {
        String sql = "SELECT * FROM column_tbl WHERE deleted = :isDeleted";
        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        try {
            return namedParameterJdbcTemplate.query(sql, params, columnRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Column findById(Long id) {
        String sql = "SELECT id, title, order_index, created_at FROM column_tbl WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, columnRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Column updateOrder(Column column, Double newOrder) {
        String sql = "UPDATE column_tbl SET order_index = :order WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("order", newOrder);
        params.put("id", column.getId());

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params));

        return findById(column.getId());
    }

    @Override
    public Column updateTitle(Column column) {
        String sql = "UPDATE column_tbl SET title = :title WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("title", column.getTitle());
        params.put("id", column.getId());

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params));

        return findById((column.getId()));
    }


    @Override
    public boolean delete(Long id) {
        String sql = "UPDATE column_tbl SET deleted = :isDeleted WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", true);
        params.put("id", id);

        int countAffected = namedParameterJdbcTemplate.update(sql, params);
        return countAffected == 1;
    }

    @Override
    public Column save(Column column, Double order) {
        String sql = "INSERT INTO column_tbl (title, order_index, created_at, deleted) "
                + " VALUES (:title, :order, :createdAt, :isDeleted)";

        Map<String, Object> params = new HashMap<>();
        params.put("title", column.getTitle());
        params.put("order", order);
        params.put("createdAt", LocalDateTime.now());
        params.put("isDeleted", false);

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params),
                keyHolder);

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    public Double getLastOrder() {
        String sql = "SELECT MAX(order_index) AS last_order FROM column_tbl WHERE deleted = :isDeleted";

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);

        Double lastOrder = namedParameterJdbcTemplate.queryForObject(
                sql, params, Double.class);
        return lastOrder;
    }

    private RowMapper<Column> columnRowMapper() {
        return ((rs, rowNum) ->
                Column.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .order(rs.getDouble("order_index"))
                        .createdAt(rs.getObject("created_at", LocalDateTime.class))
                        .build()
        );
    }
}
