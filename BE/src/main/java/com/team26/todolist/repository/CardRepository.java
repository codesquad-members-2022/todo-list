package com.team26.todolist.repository;

import com.team26.todolist.domain.Card;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Card> cardRowMapper = cardRowMapper();
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public CardRepository(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Card save(Card card, Double order) {
        String sql =
                "INSERT INTO card (user_id, title, contents, column_id, order_index, deleted, created_at) "
                        +
                        "VALUES (:userId, :title, :contents, :columnId, :order, :isDeleted, :createdAt)";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", card.getUserId());
        params.put("title", card.getTitle());
        params.put("contents", card.getContents());
        params.put("columnId", card.getColumnId());
        params.put("order", order);
        params.put("isDeleted", false);
        params.put("createdAt", LocalDateTime.now());

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params),
                keyHolder);

        return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    public List<Card> findByColumnId(Long columnId) {
        String sql = "SELECT id, user_id, title, contents, column_id, order_index, created_at " +
                "FROM card " +
                "WHERE deleted = :isDeleted AND column_id = :columnId";

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("columnId", columnId);

        try {
            return namedParameterJdbcTemplate.query(sql, params, cardRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Card findById(Long id) {
        String sql = "SELECT id, user_id, title, contents, column_id, order_index, created_at " +
                "FROM card " +
                "WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, cardRowMapper);
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void delete(Card findCard) {
        String sql = "UPDATE card SET deleted = :isDeleted WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", true);
        params.put("id", findCard.getId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public Card update(Card cardBefore) {
        String sql = "UPDATE card SET title = :title, contents = :contents WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("title", cardBefore.getTitle());
        params.put("contents", cardBefore.getContents());
        params.put("id", cardBefore.getId());

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params),
                keyHolder);

        return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    public Card updateLocation(Card card, Double newOrder) {
        String sql = "UPDATE card SET column_id = :columnId, order_index = :order WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("columnId", card.getColumnId());
        params.put("order", newOrder);
        params.put("id", card.getId());

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params),
                keyHolder);

        return findById(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    private RowMapper<Card> cardRowMapper() {
        return ((rs, rowNum) ->
                new Card(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("user_id"),
                        rs.getLong("column_id"),
                        rs.getDouble("order_index"),
                        rs.getObject("created_at", LocalDateTime.class)
                ));
    }

    public Double getFirstOrder() {
        String sql = "SELECT MIN(order_index) AS first_order FROM card WHERE deleted = :isDeleted";

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);

        Double firstOrder = namedParameterJdbcTemplate.queryForObject(
                sql, params, Double.class);
        return firstOrder;
    }
}
