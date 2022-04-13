package com.example.backend.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.backend.domain.ActionType;
import com.example.backend.domain.Log;
import com.example.backend.web.dto.LogListResponseDto;

@Repository
public class LogJdbcTemplateRepository {
	private static final Logger logger = LoggerFactory.getLogger(LogJdbcTemplateRepository.class);
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert simpleJdbcInsert;

	public LogJdbcTemplateRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("ACTION_LOG")
			.usingGeneratedKeyColumns("id", "created_date", "author");
	}

	public List<LogListResponseDto> findAll() {
		return jdbcTemplate.query(
			"SELECT author, title, prev_column_name, cur_column_name, action_type, created_date FROM ACTION_LOG ORDER BY id DESC",
			(rs, rowNum) -> {
				String author = rs.getString("author");
				String title = rs.getString("title");
				String prevColumnName = rs.getString("prev_column_name");
				String curColumnName = rs.getString("cur_column_name");
				String actionType = rs.getString("action_type");
				String created_date = rs.getString("created_date");
				Log log = new Log.Builder().author(author)
					.title(title)
					.prevColumnName(prevColumnName)
					.curColumnName(curColumnName)
					.actionType(ActionType.valueOf(actionType.toUpperCase(Locale.ROOT)))
					.createdDate(created_date)
					.build();
				return new LogListResponseDto(author, log.createText(), created_date);
			});
	}

	public Long save(Log log) {
		Map<String, Object> params = new HashMap<>();
		params.put("title", log.getTitle());
		params.put("prev_column_name", log.getPrevColumnName());
		params.put("cur_column_name", log.getCurColumnName());
		params.put("action_type", log.getActionType().getName());
		KeyHolder keyHolder = simpleJdbcInsert.executeAndReturnKeyHolder(params);
		Map<String, Object> keys = keyHolder.getKeys();
		logger.debug("LogJdbcTemplateRepository save : {}", keys);
		return 0L;
	}
}
