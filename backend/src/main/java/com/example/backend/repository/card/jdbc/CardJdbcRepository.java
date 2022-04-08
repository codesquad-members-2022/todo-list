package com.example.backend.repository.card.jdbc;

import com.example.backend.domain.card.Card;
import com.example.backend.repository.card.CardRepository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

@Repository
public class CardJdbcRepository implements CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CardJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public Card save(Card card) {
        String query = "insert cards (id, title, content, card_type, created_at, last_modified_at, `visible`, column_id)" +
                "values (:id, :title, :content, :cardType, :createdAt, :lastModifiedAt, :visible, :columnId)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(toParamMap(card));
        jdbcTemplate.update(query, mapSqlParameterSource, keyHolder);
        return null;
    }

    private Map<String, Object> toParamMap(Card card) {
        return new HashMap<>() {{
            put("id", card.getId());
            put("title", card.getTitle());
            put("content", card.getContent());
            put("cardType", card.getCardType().toString());
            put("createdAt", LocalDateTime.now());
            put("lastModifiedAt", LocalDateTime.now());
            put("visible", true);
            put("columnId", card.getColumnId());
        }};
    }
}
