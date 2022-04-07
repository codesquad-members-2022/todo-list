package com.codesquad.todolist.card;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CardRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Card create(Card newCard) {
        String sql = "insert into card(card_id, column_id, author, created_date, title, content, card_order, deleted)"
            + " values(:cardId, :columnId, :author, :createdDate, :title, :content, :cardOrder, :deleted)";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newCard));
        return newCard;
    }

    public Optional<Card> findById(int cardId) {
        String sql = "select * from card where card_id = :cardId and deleted = false";
        Card card = jdbcTemplate.queryForObject(sql,
            new MapSqlParameterSource().addValue("cardId", cardId),
            getCardRowMapper());
        return Optional.ofNullable(card);
    }

    private RowMapper<Card> getCardRowMapper() {
        return (rs, rowNum) ->
            new Card.Builder(rs.getInt("columnId"),
                rs.getString("title"),
                rs.getString("author"))
                .content(rs.getString("content"))
                .build();
    }
}
