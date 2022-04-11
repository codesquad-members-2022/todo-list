package com.team15.todoapi.repository;

import com.team15.todoapi.domain.Card;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCardRepository implements CardRepository{

	private final JdbcTemplate jdbcTemplate;

	public JdbcCardRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Card> findAll() {
		return jdbcTemplate.query(
			"SELECT card.id, "
				+ "title, "
				+ "content, "
				+ "modified_at, "
				+ "user_id, "
				+ "card_section_code_id "
				+ "from card , member "
				+ "where card.member_id = member.id "
				+ "and  delete_flag = false "
				+ "order by modified_at desc", mapper());
	}

	private RowMapper<Card> mapper(){
		return (rs, rowNum) -> {
			Card card = Card.of(rs.getLong("id"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getString("user_id"),
				rs.getTimestamp("modified_at").toLocalDateTime(),
				rs.getInt("card_section_code_id"));
			return card;
		};
	}
}
