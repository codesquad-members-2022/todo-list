package com.example.todo.repository;

import com.example.todo.domain.Card;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcCardRepository implements CardRepository{

	private final JdbcTemplate jdbcTemplate;

	public JdbcCardRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Card> findAll() {
		return null;
	}
}
