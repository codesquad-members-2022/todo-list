package com.team26.todolist.repository;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardStatus;
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

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Card> cardRowMapper = cardRowMapper();
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public CardRepository(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Card save(Card card) {
        String sql = "INSERT INTO card (user_id, title, contents, card_status, deleted, created_at) " +
                "VALUES (:user_id, :title, :contents, :card_status, :deleted, :created_at)";

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", card.getUserId());
        params.put("title", card.getTitle());
        params.put("contents", card.getContents());
        params.put("card_status", card.getCardStatus().name());
        params.put("deleted", false);
        params.put("created_at", LocalDateTime.now());

        long saveId = namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params), keyHolder);

        return findById(saveId);
    }

    public List<Card> findByCardStatus(String cardStatus) {
        String sql = "SELECT id, user_id, title, contents, card_status, created_at " +
                "FROM card " +
                "WHERE deleted = :isDeleted AND card_status = :card_status";

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("card_status", cardStatus);

        return namedParameterJdbcTemplate.query(sql, params, cardRowMapper);
    }

    public Card findById(Long id) {
        String sql = "SELECT id, user_id, title, contents, card_status, created_at " +
                "FROM card " +
                "WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, params, cardRowMapper);
    }

    public void delete(Card findCard) {
        String sql = "UPDATE card " +
                "SET deleted = :deleted " +
                "WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("deleted", true);
        params.put("id", findCard.getId());

        namedParameterJdbcTemplate.update(sql,params);
    }

    public Card update(Card cardBefore) {
        String sql = "UPDATE card " +
                "SET title = :title, contents = :contents " + 
                "WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("title", cardBefore.getTitle());
        params.put("contents", cardBefore.getContents());
        params.put("id", cardBefore.getId());

        long saveId = namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params), keyHolder);

        return findById(saveId);
    }

    public Card updateCardStatus(Card cardBefore) {
        String sql = "UPDATE card " +
                "SET card_status = :cardStatus " +
                "WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("cardStatus", cardBefore.getCardStatus().name());
        params.put("id", cardBefore.getId());

        long saveId = namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValues(params), keyHolder);

        return findById(saveId);
    }

    private RowMapper<Card> cardRowMapper() {
        return ((rs, rowNum) ->
                new Card(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("user_id"),
                        CardStatus.valueOf(rs.getString("card_status")),
                        rs.getObject("created_at", LocalDateTime.class)
                ));
    }
}
