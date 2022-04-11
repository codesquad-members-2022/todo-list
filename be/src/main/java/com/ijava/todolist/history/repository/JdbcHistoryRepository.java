package com.ijava.todolist.history.repository;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.exception.HistoryNotSavedException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class JdbcHistoryRepository implements HistoryRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcHistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("history");
    }

    @Override
    public void save(History history) {
        try {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", history.getUserId())
                .addValue("card_id", history.getCardId())
                .addValue("columns_id", history.getColumnsId())
                .addValue("action", history.getAction().name())
                .addValue("created_date", history.getCreatedDate())
                .addValue("modified_date", history.getModifiedDate());

            simpleJdbcInsert.execute(sqlParameterSource);
            log.debug(simpleJdbcInsert.getInsertString(), sqlParameterSource);
        } catch (Exception e) {
            throw new HistoryNotSavedException();
        }
    }

    @Override
    public Optional<List<History>> findAll() {
        String findAllSql = "SELECT id,user_id,card_id,columns_id,action,created_date,modified_date FROM history";
        List<History> historyList = jdbcTemplate.query(findAllSql, historyRowMapper());
        log.debug(findAllSql, historyList);
        return historyList.isEmpty() ? Optional.empty() : Optional.of(historyList);
    }

    private RowMapper<History> historyRowMapper() {
        return (rs, rowNum) -> new History(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getLong("card_id"),
            rs.getLong("columns_id"),
            Action.valueOf(rs.getString("action")),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("modified_date").toLocalDateTime()
        );
    }
}
