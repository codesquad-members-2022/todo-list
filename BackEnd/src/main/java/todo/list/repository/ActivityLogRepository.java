package todo.list.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import todo.list.domain.Action;
import todo.list.domain.ActivityLog;
import todo.list.domain.CardStatus;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ActivityLogRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<ActivityLog> findAll() {
        return jdbcTemplate.query("select * from activity_log order by create_date desc", activityLogLowMapper());
    }

    private RowMapper<ActivityLog> activityLogLowMapper() {
        return (rs, rowNum) -> {
            Long id = rs.getLong("id");
            Action action = Action.from(rs.getString("action"));
            String title = rs.getString("title");
            CardStatus nowStatus = CardStatus.from(rs.getString("now_status"));
            CardStatus beforeStatus = CardStatus.from(rs.getString("before_status"));
            LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();
            return new ActivityLog(id, action, title, nowStatus, beforeStatus, createDate);
        };
    }
}
