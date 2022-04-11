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
        Card card = null;
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
        jdbcTemplate.update(
            "update card set title = :title, content = :content, author = :author where card_id = :cardId and deleted = false",
            new BeanPropertySqlParameterSource(card));
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
