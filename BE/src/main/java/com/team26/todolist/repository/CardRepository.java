package com.team26.todolist.repository;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CardRepository(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Card> findByCardStatus(String cardStatus) {
        String sql = "SELECT id, user_id, title, contents, card_status, created_at " +
                "FROM card " +
                "WHERE isDeleted = :isDeleted AND card_status = :card_status";

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("card_status", cardStatus);

        return namedParameterJdbcTemplate.query(sql, params, cardRowMapper());
    }

    private RowMapper<Card> cardRowMapper() {
        return ((rs, rowNum) ->
                new Card(
                        rs.getLong("id"),
                        rs.getString("user_id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        CardStatus.createCardStatus(rs.getString("card_status")),
                        rs.getObject("created_at", LocalDateTime.class)
                ));
    }
}
