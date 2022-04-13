package com.example.backend.controller.history;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.history.History;
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

import static com.example.backend.utils.TimeUtils.dateTimeOf;

@Repository
public class HistoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<History> findHistories(Long memberId, Long cardId) {
        String query = "SELECT m.nick_name, c.title, c.content, c.created_at, c.card_type " +
                "FROM member AS m, card AS c JOIN c.member_id ON m.id";
        return jdbcTemplate.query(query, mapper);
    }

    private static final RowMapper<History> mapper = (rs, rowNum) ->
            new History(
                    rs.getLong("id"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at")),
                    rs.getString("action"),
                    rs.getLong("member_id"),
                    rs.getLong("card_id"),
                    rs.getBoolean("visible")
            );

    public History save(History history) {
        String query = "insert history (id, content, created_at, `action`, member_id, card_id, font, `visible`)" +
                "values (:id, :content, :created_at, :action, :member_id, :card_id, :font, :visible)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(toParamMap(history));
        jdbcTemplate.update(query, mapSqlParameterSource, keyHolder);
        return history;
    }

    private Map<String, Object> toParamMap(History history) {
        return new HashMap<>() {{
            put("id", history.getId());
            put("content", history.getContent());
            put("created_at", LocalDateTime.now());
            put("action", history.getAction().name());
            put("member_id", history.getMemberId());
            put("card_id", history.getCardId());
            put("font", history.getFont());
            put("visible", true);
        }};
    }
}
