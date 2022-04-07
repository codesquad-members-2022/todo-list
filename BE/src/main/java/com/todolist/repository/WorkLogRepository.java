package com.todolist.repository;

import com.todolist.domain.WorkLog;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkLogRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public WorkLogRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<WorkLog> findAllWorkLogs(String userId) {
        return jdbc.query("SELECT user_id, title, action, previous_status, changed_status, updated_datetime "
            + "FROM work AS A JOIN work_log AS B ON A.id = B.work_id WHERE user_id = :userId ORDER BY updated_datetime DESC",
            Collections.singletonMap("userId", userId), workLogRowMapper());
    }

    private RowMapper<WorkLog> workLogRowMapper() {
        return (rs, rowNum) -> {
            WorkLog workLog = new WorkLog(
                rs.getString("user_id"),
                rs.getString("title"),
                rs.getString("action"),
                rs.getString("previous_status"),
                rs.getString("changed_status"),
                rs.getTimestamp("updated_datetime").toLocalDateTime()
            );

            return workLog;
        };
    }
}
