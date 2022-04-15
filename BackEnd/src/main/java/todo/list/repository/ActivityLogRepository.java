package todo.list.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import todo.list.domain.Action;
import todo.list.domain.ActivityLog;
import todo.list.domain.CardStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ActivityLogRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ActivityLogRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ActivityLog> findAllOrderByCreateDatetimeDesc() {
        return jdbcTemplate.query("select id, activity_log_action, title, now_status, before_status, create_datetime from activity_log order by create_datetime desc", activityLogRowMapper());
    }

    private RowMapper<ActivityLog> activityLogRowMapper() {
        return (rs, rowNum) -> {
            Long id = rs.getLong("id");
            Action action = Action.valueOf(rs.getString("activity_log_action"));
            String title = rs.getString("title");
            CardStatus nowStatus = CardStatus.from(rs.getString("now_status"));
            CardStatus beforeStatus = CardStatus.from(rs.getString("before_status"));
            LocalDateTime createDate = rs.getTimestamp("create_datetime").toLocalDateTime();
            return new ActivityLog(id, action, title, nowStatus, beforeStatus, createDate);
        };
    }

    public void save(ActivityLog activityLog) {
        String sql = "insert into activity_log (activity_log_action, title, now_status, create_datetime) " +
                                        "values(:activity_log_action, :title, :now_status, :create_datetime)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("activity_log_action", activityLog.getAction().name())
                .addValue("title", activityLog.getTitle())
                .addValue("now_status", activityLog.getNowStatus().name())
                .addValue("create_datetime", activityLog.getCreateDateTime());

        if (activityLog.actionIsMove()) {
            sql = "insert into activity_log (activity_log_action, title, now_status, before_status, create_datetime) " +
                                    "values(:activity_log_action, :title, :now_status, :before_status, :create_datetime)";
            params.addValue("before_status", activityLog.getBeforeStatus().name());
        }
        jdbcTemplate.update(sql, params);
    }
}
