package com.codesquad.todolist.card;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.codesquad.todolist.util.KeyHolderFactory;

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
        KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

        jdbcTemplate.update(
            "insert into card (column_id, title, content, author, card_order, created_date) values (:columnId, :title, :content, :author, :order, :createdDateTime)",
            new BeanPropertySqlParameterSource(card), keyHolder);

        if (keyHolder.getKey() != null) {
            card.setCardId(keyHolder.getKey().intValue());
        }
        return card;
    }

    public Optional<Card> findById(int cardId) {
        String sql = "select card_id, column_id, title, content, author, card_order, created_date from card where card_id = :cardId and deleted = false";
        Card card;
        try {
            card = jdbcTemplate.queryForObject(sql, new MapSqlParameterSource().addValue("cardId", cardId),
                getCardRowMapper());

            return Optional.ofNullable(card);

        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public Integer countByColumn(int columnId) {
        String sql = "select count(*) from card where column_id = :columnId";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("columnId", columnId), Integer.class);
    }

    public void update(Card card) {
        String sql = "update card set title = :title, content = :content, author = :author where card_id = :cardId and deleted = false";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card));
    }

    public void move(int oldNextId, Card card) {
        // `nextId = 이동되는카드(2)` 였던 카드(3)를 찾고, 해당 카드의 nextId를 oldNextId로 변경한다
        String updateCard3Sql = "update card set next_id = :oldNextId where next_id = :cardId";
        jdbcTemplate.update(updateCard3Sql, new MapSqlParameterSource()
            .addValue("oldNextId", oldNextId)
            .addValue("cardId", card.getCardId())
        );
        // 이동된 자리에 있던 카드(5)를 찾아서, `nextId = 카드(0)` 으로 바꿔준다. (카드(5)의 조건은 `nextId = 카드(0).nextId`)
        String updateCard5Sql = "update card set next_id = :cardId where next_id = :newNextId";
        jdbcTemplate.update(updateCard5Sql, new MapSqlParameterSource()
            .addValue("cardId", card.getCardId())
            .addValue("newNextId", card.getNextId())
        );

        String sql = "update card set column_id = :columnId, next_id = :nextId where card_id = :cardId";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card));
    }

    public void deleteById(int cardId) {
        jdbcTemplate.update(
            "update card set deleted = true where card_id = :cardId",
            new MapSqlParameterSource().addValue("cardId", cardId));
    }

    private RowMapper<Card> getCardRowMapper() {
        return (rs, rowNum) -> new Card(
            rs.getInt("card_id"),
            rs.getInt("column_id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getString("author"),
            rs.getInt("card_order"),
            rs.getObject("created_date", LocalDateTime.class));
    }
}
