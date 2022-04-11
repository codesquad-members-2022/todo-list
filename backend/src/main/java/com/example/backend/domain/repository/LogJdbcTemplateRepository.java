package com.example.backend.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.backend.domain.Log;
import com.example.backend.web.dto.LogListResponseDto;

@Repository
public class LogJdbcTemplateRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert simpleJdbcInsert;

	public LogJdbcTemplateRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("ACTION_LOG")
			.usingGeneratedKeyColumns("id");
	}

	public List<LogListResponseDto> findAll() {
		return jdbcTemplate.query(
			"SELECT title, prev_column_name, cur_column_name, action_type, created_date FROM ACTION_LOG ORDER BY id DESC",
			(rs, rowNum) -> {
				String title = rs.getString("title");
				String prev_column_name = rs.getString("prev_column_name");
				String cur_column_name = rs.getString("cur_column_name");
				String action_type = rs.getString("action_type");
				String created_date = rs.getString("created_date");
				return new LogListResponseDto(title, prev_column_name, cur_column_name, action_type, created_date);
			});
	}

	public Long save(Log log) {
		Map<String, Object> params = new HashMap<>();
		params.put("title", log.getTitle());
		params.put("prev_column_name", log.getPrevColumnName());
		params.put("cur_column_name", log.getCurrentColumnName());
		params.put("action_type", log.getActionType());
		return simpleJdbcInsert.executeAndReturnKey(params).longValue();
	}
}
