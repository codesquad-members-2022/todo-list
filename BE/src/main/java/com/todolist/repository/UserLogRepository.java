package com.todolist.repository;

import com.todolist.domain.UserLog;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.stereotype.Repository;

@Repository
public class UserLogRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public UserLogRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<UserLog> findAllByUserId(String userId) {
        return jdbc.query("SELECT title, action, previous_column, changed_column, updated_datetime "
            + "FROM user_log WHERE user_id = :userId ORDER BY updated_datetime DESC",
            Collections.singletonMap("userId", userId), userLogRowMapper());
    }

    public void saveCreationLog(UserLog userLog) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(userLog);
        jdbc.update("INSERT INTO user_log (user_id, title, action, previous_column, updated_datetime)"
            + " VALUES (:userId, :title, :action, :previousColumn, :updatedDateTime)", parameters);
    }

    private RowMapper<UserLog> userLogRowMapper() {
        return (rs, rowNum) -> {
            UserLog userLog = new UserLog(
                rs.getString("title"),
                rs.getString("action"),
                rs.getString("previous_column"),
                rs.getString("changed_column"),
                rs.getTimestamp("updated_datetime").toLocalDateTime()
            );

            return userLog;
        };
    }
}
