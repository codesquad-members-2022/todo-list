package com.team26.todolist.repository;

import com.team26.todolist.domain.CardAction;
import com.team26.todolist.domain.CardStatus;
import com.team26.todolist.domain.History;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public HistoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<History> findAll() {
        String sql = "SELECT * FROM HISTORY";
        return jdbcTemplate.query(sql, historyRowMapper());
    }

    @Override
    public History findById(Long id) {
        String sql = "SELECT * FROM HISTORY WHERE id = ?";
        List<History> result = jdbcTemplate.query(sql, historyRowMapper(), id);

        return result.stream().findAny().orElse(null);
    }


    @Override
    public History save(History history) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("history").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("card_action", history.getCardAction());
        parameters.put("user_id", history.getUserId());
        parameters.put("card_title", history.getCardTitle());
        parameters.put("card_title_before", history.getCardTitleBefore());
        parameters.put("card_status", history.getCardStatus().name());
        parameters.put("card_status_before", history.getCardStatusBefore().name());
        parameters.put("created_at", history.getCreatedAt());

        Long key = (Long) jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        history.initId(key);
        return history;
    }

    private RowMapper<History> historyRowMapper() {
        return (rs, rowNum) ->
                History.builder(CardAction.valueOf(rs.getString("card_action")),
                                rs.getString("user_id"),
                                rs.getTimestamp("created_at").toLocalDateTime())
                        .cardTitle(rs.getString("card_title"))
                        .cardTitleBefore(rs.getString("card_title_before"))
                        .cardStatus(CardStatus.valueOf(rs.getString("card_status")))
                        .cardStatusBefore(CardStatus.valueOf(rs.getString("card_status_before")))
                        .build();
    }
}
