package com.example.backend.controller.history;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.history.History;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.example.backend.utils.TimeUtils.dateTimeOf;

@Repository
public class HistoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<History> findHistories(Long id) {
        String query = "SELECT author, title, content, created_at, action, last_modified_at FROM history WHERE id=:id";
        return null;
    }

    private static final RowMapper<History> mapper = (rs, rowNum) ->
            new History(
                    rs.getLong("id"),
                    rs.getString("author"),
                    rs.getString("title"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at")),
                    rs.getString("action"),
                    rs.getLong("member_id"),
                    rs.getBoolean("visible")
            );
}
