package com.codesquad.todolist.card;

import com.codesquad.todolist.util.KeyHolderFactory;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final KeyHolderFactory keyHolderFactory;

    public CardRepository(NamedParameterJdbcTemplate jdbcTemplate,
        KeyHolderFactory keyHolderFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.keyHolderFactory = keyHolderFactory;
    }

    public Card create(Card card) {
        String sql = "insert into card (column_id, title, content, author, next_id, created_date)"
            + " values (:columnId, :title, :content, :author, :nextId, :createdDateTime)";
        KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card), keyHolder);

        if (keyHolder.getKey() != null) {
            card.setCardId(keyHolder.getKey().intValue());
        }
        return card;
    }

    public Optional<Card> findById(int cardId) {
        String sql =
            "select card_id, column_id, title, content, author, next_id, created_date from card"
                + " where card_id = :cardId and deleted = false";
        Card card;
        try {
            card = jdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("cardId", cardId),
                getCardRowMapper());

            return Optional.ofNullable(card);

        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public void update(Card card) {
        String sql = "update card set title = :title, content = :content, author = :author"
            + " where card_id = :cardId and deleted = false";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card));
    }

    public void linkPrev(Card card) {
        // 이동할 카드의 기존 (prev) 위치에서, 이전 노드에 해당하는 카드를 새로 연결한다
        String sql = "update card set next_id = :nextId where next_id = :cardId";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card));
    }

    public void linkNext(Card card) {
        // 이동할 카드의 이동 후 (next) 위치에서, 이전 노드에 해당하는 카드를 새로 연결한다
        String sql = card.getNextId() == null
            ? "update card set next_id = :cardId where next_id is null and column_id = :columnId"
            : "update card set next_id = :cardId where next_id = :nextId";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card));
    }

    public void moveTarget(Card card) {
        // 이동할 카드를 지정된 컬럼으로 이동한다
        String sql = "update card set column_id = :columnId, next_id = :nextId where card_id = :cardId";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card));
    }

    public void deleteTarget(Card card) {
        String sql = "update card set deleted = true, next_id = null where card_id = :cardId";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card));
    }

    private RowMapper<Card> getCardRowMapper() {
        return (rs, rowNum) -> new Card(
            rs.getInt("card_id"),
            rs.getInt("column_id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getString("author"),
            rs.getObject("next_id", Integer.class),
            rs.getObject("created_date", LocalDateTime.class));
    }
}
