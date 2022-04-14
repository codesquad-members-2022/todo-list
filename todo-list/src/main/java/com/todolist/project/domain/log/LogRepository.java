package com.todolist.project.domain.log;

import com.todolist.project.web.dto.LogListDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LogRepository {

	private static final String LOG_SELECT_SQL
		= "SELECT title, current_status, prev_status, action_status, action_time FROM LOG";
	private static final String LOG_INSERT_SQL
		= "INSERT INTO LOG (title, current_status, prev_status, action_status, action_time) VALUES (:title, :current_status, :prev_status, :action_status, :action_time)";
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public LogRepository(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<LogListDto> findAll() {
		return namedParameterJdbcTemplate.query(LOG_SELECT_SQL,
			(rs, rowNum) -> {
				String title = rs.getString("title");
				String prev_status = rs.getString("prev_status");
				String current_status = rs.getString("current_status");
				String actionStatus = rs.getString("action_status");
				LocalDateTime action_time = rs.getTimestamp("action_time").toLocalDateTime();
				return new LogListDto(title, prev_status, current_status, actionStatus,
					action_time);
			}
		);
	}

	public int save(Log log) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("title", log.getTitle());
		map.addValue("prev_status", log.getPrevStatus().name());
		map.addValue("current_status", log.getCurrentStatus().name());
		map.addValue("action_status", log.getActionStatus().name());
		map.addValue("action_time", log.getActionTime());
		return namedParameterJdbcTemplate.update(LOG_INSERT_SQL,
			map);
	}

}
