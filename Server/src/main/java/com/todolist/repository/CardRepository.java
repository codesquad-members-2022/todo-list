package com.todolist.repository;

import com.todolist.domain.Card;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepository {

    private final JdbcTemplate jdbcTemplate;

    public CardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Card> findAllCards() {
        String sql = "SELECT * FROM card WHERE remove = 0";
        return jdbcTemplate.query(sql, cardRowMapper());
    }

    private RowMapper<Card> cardRowMapper() {
        return (resultSet, rowNum) -> {
            Card card = new Card(
                    resultSet.getInt("cardId"),
                    resultSet.getString("cardTitle"),
                    resultSet.getString("cardContent"),
                    resultSet.getTimestamp("addDateTime").toLocalDateTime(),
                    resultSet.getInt("internalOrder"),
                    resultSet.getString("boardName")
                    );
            return card;
        };
    }
}
