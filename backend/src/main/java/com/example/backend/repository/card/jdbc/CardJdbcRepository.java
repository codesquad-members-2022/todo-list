package com.example.backend.repository.card.jdbc;

import static com.example.backend.utils.TimeUtils.dateTimeOf;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import com.example.backend.repository.card.CardRepository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

@Repository
public class CardJdbcRepository implements CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CardJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public Card save(Card card) {
        String query = "insert todo_list.card (id, title, content, card_type, created_at, last_modified_at, `visible`, column_id)" +
                "values (:id, :title, :content, :cardType, :createdAt, :lastModifiedAt, :visible, :columnId)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(toParamMap(card));
        jdbcTemplate.update(query, mapSqlParameterSource, keyHolder);
        return null;
    }

    @Override
    public Map<String, List<Card>> findAll() {
        String query = "select todo_list.card.id, " +
                "todo_list.card.title, " +
                "todo_list.card.content, " +
                "todo_list.card.card_type, " +
                "todo_list.card.created_at, " +
                "todo_list.card.last_modified_at, " +
                "todo_list.card.visible, " +
                "todo_list.card.column_id " +
                "from todo_list.card where todo_list.card.card_type = :cardType";
        return cardTypeClassification(query);
    }

    @Override
    public Optional<Card> findById(Long id) {
        String query = "select todo_list.card.id, " +
                "todo_list.card.title, " +
                "todo_list.card.content, " +
                "todo_list.card.card_type, " +
                "todo_list.card.created_at, " +
                "todo_list.card.last_modified_at, " +
                "todo_list.card.visible, " +
                "todo_list.card.column_id " +
                "from todo_list.card where todo_list.card.id = :id";
        Map<String, Object> params = Collections.singletonMap("id", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, params, mapper));
    }

    @Override
    public Card update(Card card) {
        String query = "update todo_list.card set title=:title, content=:content, card_type=:cardType where id=:id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(toParamMap(card));
        jdbcTemplate.update(query, mapSqlParameterSource);
        return null;
    }

    @Override
    public Card delete(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        String query = "update todo_list.card set visible=false where id=:id";
        jdbcTemplate.update(query, namedParameters);
        return null;
    }

    private Map<String, List<Card>> cardTypeClassification(String query) {
        Map<String, List<Card>> cardMap = new HashMap<>();
        CardType[] type = CardType.values();
        for (CardType cardType : type) {
            Map<String, Object> params = Collections.singletonMap("cardType", cardType.toString());
            List<Card> cards = jdbcTemplate.query(query, params, mapper);
            cardMap.put(cardType.toString(), cards);
        }
        return cardMap;
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

    private static RowMapper<Card> mapper = (rs, rowNum) ->
            new Card(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("card_type"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at")),
                    rs.getBoolean("visible"),
                    rs.getLong("column_id")
            );
}
