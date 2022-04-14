package com.todolist.repository;

import com.todolist.domain.UserLog;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
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
        return jdbc.query("SELECT title, action, previous_category, current_category, updated_datetime "
            + "FROM user_log WHERE user_id = :userId ORDER BY updated_datetime DESC",
            Collections.singletonMap("userId", userId), userLogRowMapper());
    }

    public void saveLogOfCreationByUser(UserLog userLog) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(userLog);
        jdbc.update("INSERT INTO user_log (user_id, title, action, current_category, updated_datetime)"
            + " VALUES (:userId, :title, :action, :currentCategory, :updatedDateTime)", parameters);
    }

    public void saveLogOfMovementByUser(UserLog userLog) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(userLog);
        jdbc.update("INSERT INTO user_log (user_id, title, action, previous_category, current_category, updated_datetime)"
            + " VALUES (:userId, :title, :action, :previousCategory, :currentCategory, :updatedDateTime)", parameters);
    }

    public void saveLogOfDeletionByUser(UserLog userLog) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(userLog);
        jdbc.update("INSERT INTO user_log (user_id, title, action, current_category, updated_datetime)"
            + " VALUES (:userId, :title, :action, :currentCategory, :updatedDateTime)", parameters);
    }

    public void saveLogOfModificationByUser(UserLog userLog) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(userLog);
        jdbc.update("INSERT INTO user_log (user_id, title, action, current_category, updated_datetime)"
            + " VALUES (:userId, :title, :action, :currentCategory, :updatedDateTime)", parameters);
    }

    private RowMapper<UserLog> userLogRowMapper() {
        return (rs, rowNum) -> {
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

            UserLog userLog = UserLog.builder()
                .title(rs.getString("title"))
                .action(rs.getString("action"))
                .previousCategory(rs.getString("previous_category"))
                .currentCategory(rs.getString("current_category"))
                .updatedDateTime(rs.getTimestamp("updated_datetime", cal).toLocalDateTime())
                .build();

            return userLog;
        };
    }
}
