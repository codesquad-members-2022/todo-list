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
            "insert into card (column_id, title, content, author, next_id, created_date) values (:columnId, :title, "
                + ":content, :author, :nextId, :createdDateTime)",
            new BeanPropertySqlParameterSource(card), keyHolder);

        if (keyHolder.getKey() != null) {
            card.setCardId(keyHolder.getKey().intValue());
        }
        return card;
    }

    public Optional<Card> findById(int cardId) {
        String sql = "select card_id, column_id, title, content, author, next_id, created_date from card where "
            + "card_id = :cardId and deleted = false";
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

    public void move(Integer oldNextId, Card card) {
        // 기존 위치의 카드의 nextId 를 변경한다
        String updateCard3Sql = "update card set next_id = :oldNextId where next_id = :cardId";
        jdbcTemplate.update(updateCard3Sql, new MapSqlParameterSource()
            .addValue("oldNextId", oldNextId)
            .addValue("cardId", card.getCardId())
        );
        // 이동 위치의 카드의 nextId를 변경한다
        if (card.getNextId() == null) {
            String updateCard5Sql = "update card set next_id = :cardId where next_id is null and column_id = :columnId";
            jdbcTemplate.update(updateCard5Sql, new MapSqlParameterSource()
                .addValue("cardId", card.getCardId())
                .addValue("columnId", card.getColumnId())
            );
        } else {
            String updateCard5Sql = "update card set next_id = :cardId where next_id = :newNextId";
            jdbcTemplate.update(updateCard5Sql, new MapSqlParameterSource()
                .addValue("cardId", card.getCardId())
                .addValue("newNextId", card.getNextId())
            );
        }

        // 이동 하는 카드의 nextId를 변경한다
        String sql = "update card set column_id = :columnId, next_id = :nextId where card_id = :cardId";
        jdbcTemplate.update(sql, new MapSqlParameterSource()
            .addValue("columnId", card.getColumnId())
            .addValue("nextId", card.getNextId())
            .addValue("cardId", card.getCardId())
        );
    }

    public void delete(Card card) {
        // 삭제하게될 카드(3)을 바라보고 있는 카드(4)를 찾아서 nextId = 카드(3).nextId 로 변경
        String updateCard4Sql = "update card set next_id = :nextId where next_id = :cardId";
        jdbcTemplate.update(updateCard4Sql, new MapSqlParameterSource()
            .addValue("nextId", card.getNextId())
            .addValue("cardId", card.getCardId())
        );

        // 카드(3) 삭제
        String updateCard3Sql = "update card set deleted = true, next_id = null where card_id = :cardId";
        jdbcTemplate.update(updateCard3Sql, new MapSqlParameterSource("cardId", card.getCardId()));
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
