package com.hooria.todo.repository;

import com.hooria.todo.domain.ActivityLog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class ActivityLogRepository {

    private final SimpleJdbcInsert insertJdbc;
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<ActivityLog> rowMapper;

    public ActivityLogRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
        insertJdbc = new SimpleJdbcInsert(dataSource)
                .withTableName("activity_log")
                .usingGeneratedKeyColumns("id");

        rowMapper = (rs, row) ->
                new ActivityLog(
                        rs.getLong("id"),
                        rs.getString("user_id"),
                        rs.getInt("activity_type"),
                        rs.getInt("from_status"),
                        rs.getInt("to_status"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getBoolean("read_yn")
                );
    }

    public ActivityLog insert(ActivityLog activityLog) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(activityLog);
        insertJdbc.executeAndReturnKey(params).intValue();
        return activityLog;
    }

    public List<ActivityLog> findAll() {
        return jdbc.query(
                "select id, user_id, activity_type, from_status, to_status, created_at, read_yn from activity_log",
                Collections.emptyMap(), rowMapper
        );
    }

    public int deleteById(long id) {
        return jdbc.update("update activity_log set read_yn = false where id = :id", new MapSqlParameterSource("id", id));
    }
}
