package com.example.backend.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
			.usingGeneratedKeyColumns("id", "created_date");
	}

	public List<LogListResponseDto> findAll() {
		return jdbcTemplate.query(
			"SELECT author, text, created_date FROM ACTION_LOG ORDER BY id DESC",
			(rs, rowNum) -> {
				String author = rs.getString("author");
				String text = rs.getString("text");
				String created_date = rs.getString("created_date");
				return new LogListResponseDto(author, text, created_date);
			});
	}

	public Long save(Log log) {
		Map<String, Object> params = new HashMap<>();
		params.put("author", log.getAuthor());
		params.put("text", log.getText());
		KeyHolder keyHolder = simpleJdbcInsert.executeAndReturnKeyHolder(params);
		Map<String, Object> keys = keyHolder.getKeys();
		logger.debug("LogJdbcTemplateRepository save : {}", keys);
		return 0L;
	}
}
