package com.hooria.todo.repository;

import com.hooria.todo.domain.Action;
import com.hooria.todo.domain.ActivityLog;
import com.hooria.todo.domain.Status;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                ActivityLog.of(
                        rs.getLong("id"),
                        rs.getString("user_id"),
                        Action.of(rs.getInt("action")),
                        rs.getString("task_title"),
                        Status.of(rs.getInt("from_status")),
                        Status.of(rs.getInt("to_status")),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getBoolean("read_yn")
                );
    }

    public ActivityLog insert(ActivityLog activityLog) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", activityLog.getId());
        params.put("user_id", activityLog.getUserId());
        params.put("action", activityLog.getAction().getCode());
        params.put("task_title", activityLog.getTaskTitle());
        params.put("from_status", activityLog.getFromStatus().getCode());
        params.put("to_status", activityLog.getToStatus().getCode());
        params.put("created_at", activityLog.getCreatedAt());
        params.put("read_yn", activityLog.isReadYn());

        insertJdbc.executeAndReturnKey(params).intValue();
        return activityLog;
    }

    public List<ActivityLog> findAll() {
        return jdbc.query(
                "select id, user_id, action, task_title, from_status, to_status, created_at, read_yn from activity_log",
                Collections.emptyMap(), rowMapper
        );
    }

    public int deleteById(long id) {
        return jdbc.update("update activity_log set read_yn = false where id = :id", new MapSqlParameterSource("id", id));
    }
}
