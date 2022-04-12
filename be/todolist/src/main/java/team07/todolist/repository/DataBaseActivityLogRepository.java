package team07.todolist.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import team07.todolist.domain.ActivityLog;

@Repository
public class DataBaseActivityLogRepository implements ActivityLogRepository {

	private final JdbcTemplate jdbcTemplate;
	private final RowMapper<ActivityLog> rowMapper;

	public DataBaseActivityLogRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.rowMapper = (rs, rowNum) -> new ActivityLog(
			rs.getLong("id"),
			rs.getString("title"),
			rs.getInt("type"),
			rs.getInt("previous"),
			rs.getInt("status"),
			rs.getObject("time", LocalDateTime.class)
		);
	}

	@Override
	public ActivityLog save(ActivityLog activityLog) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("ACTIVITY_LOG").usingGeneratedKeyColumns("id");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", activityLog.getTitle());
		parameters.put("type", activityLog.getType());
		parameters.put("previous", activityLog.getPrevious());
		parameters.put("status", activityLog.getStatus());
		parameters.put("time", Timestamp.valueOf(activityLog.getTime()));

		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		return new ActivityLog(activityLog, key.longValue());
	}

	@Override
	public List<ActivityLog> findAll() {
		return jdbcTemplate.query("SELECT * FROM ACTIVITY_LOG", rowMapper);
	}
}
