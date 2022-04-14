package com.example.backend.repository.history;

import com.example.backend.controller.history.HistoryResponse;
import com.example.backend.domain.history.History;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.backend.repository.history.HistoryQueryHelper.CARD_ID;
import static com.example.backend.repository.history.HistoryQueryHelper.MEMBER_ID;
import static com.example.backend.utils.TimeUtils.dateTimeOf;

@Repository
public class HistoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 별칭
     */
    public List<HistoryResponse> findHistories(Long memberId, Long cardId) {
        String query = "SELECT h.id, h.content, h.created_at, h.action, m.member_login_id AS author, cd.card_type " +
                "FROM history AS h JOIN member AS m ON m.id=:member_id JOIN card AS cd ON cd.id=:card_id ORDER BY ㅗDESC LIMIT 10";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(MEMBER_ID, memberId)
                .addValue(CARD_ID, cardId);
        return jdbcTemplate.query(query, parameterSource, historyResponseRowMapper);
    }

    private static final RowMapper<HistoryResponse> historyResponseRowMapper = (rs, rowNum) ->
            new HistoryResponse(
                    rs.getLong("id"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    rs.getString("action"),
                    rs.getString("author"),
                    rs.getString("card_type")
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
