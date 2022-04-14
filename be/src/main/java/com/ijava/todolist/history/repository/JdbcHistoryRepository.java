package com.ijava.todolist.history.repository;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.exception.HistoryNotSavedException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

import com.ijava.todolist.history.repository.dto.JoinedHistory;
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
                .addValue("old_columns_id", history.getOldColumnsId())
                .addValue("new_columns_id", history.getNewColumnsId())
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
        String findAllSql = "SELECT id \n" +
                "     , user_id \n" +
                "     , card_id \n" +
                "     , old_columns_id \n" +
                "     , new_columns_id \n" +
                "     , action \n" +
                "     , created_date \n" +
                "     , modified_date \n" +
                "  FROM history";

        List<History> historyList = jdbcTemplate.query(findAllSql, historyRowMapper());
        log.debug(findAllSql, historyList);
        return historyList.isEmpty() ? Optional.empty() : Optional.of(historyList);
    }

    @Override
    public List<JoinedHistory> findAllJoinedHistory() {
        try {
            String finaJoinedHistorySql = "SELECT hi.id AS history_id\n " +
                    "     , hi.user_id \n " +
                    "     , us.name AS user_name \n " +
                    "     , hi.card_id\n " +
                    "     , ca.title AS card_title\n " +
                    "     , hi.old_columns_id\n " +
                    "     , oc.name AS old_columns_name\n " +
                    "     , hi.new_columns_id\n " +
                    "     , nc.name AS new_columns_name\n " +
                    "     , hi.action\n " +
                    "     , hi.created_date\n " +
                    "     , hi.modified_date\n " +
                    "  FROM history hi\n " +
                    "  LEFT JOIN card ca\n " +
                    "    ON hi.card_id = ca.id\n " +
                    "  LEFT JOIN columns oc\n " +
                    "    ON hi.old_columns_id = oc.id\n " +
                    "  LEFT JOIN columns nc\n " +
                    "    ON hi.new_columns_id = nc.id\n " +
                    "  LEFT JOIN users us\n " +
                    "    ON hi.user_id = us.id";

            List<JoinedHistory> historyList = jdbcTemplate.query(finaJoinedHistorySql, joinedHistoryRowMapper());

            log.debug(finaJoinedHistorySql, historyList);

            return historyList;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private RowMapper<History> historyRowMapper() {
        return (rs, rowNum) -> new History(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getLong("card_id"),
            rs.getLong("old_columns_id"),
            rs.getLong("new_columns_id"),
            Action.valueOf(rs.getString("action")),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("modified_date").toLocalDateTime()
        );
    }

    private RowMapper<JoinedHistory> joinedHistoryRowMapper() {
        return (rs, rowNum) -> JoinedHistory.builder()
                .id(rs.getLong("history_id"))
                .userId(rs.getLong("user_id"))
                .userName(rs.getString("user_name"))
                .cardId(rs.getLong("card_id"))
                .cardTitle(rs.getString("card_title"))
                .oldColumnsId(rs.getLong("old_columns_id"))
                .oldColumnName(rs.getString("old_columns_name"))
                .newColumnsId(rs.getLong("new_columns_id"))
                .newColumnName(rs.getString("new_columns_name"))
                .action(Action.valueOf(rs.getString("action")))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .modifiedDate(rs.getTimestamp("modified_date").toLocalDateTime())
                .build();
    }
}
