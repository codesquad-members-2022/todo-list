package com.team15.todoapi.repository;

import com.team15.todoapi.domain.Card;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCardRepository implements CardRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcCardRepository(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Card> findAll() {
		return jdbcTemplate.query(
			"SELECT card.id, "
				+ "title, "
				+ "content, "
				+ "modified_at, "
				+ "member_id, "
				+ "card_section_code_id "
				+ "from card , member "
				+ "where card.member_id = member.id "
				+ "and  delete_flag = false "
				+ "order by modified_at desc", rowMapper);
	}

	@Override
	public int add(Card card) {
		BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(card);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO card "
			+ "(id, title, content, created_at, modified_at, delete_flag, member_id, card_section_code_id) "
			+ "VALUES (:id, :title, :content, now(), now(), false, :memberId, :section)";

		return jdbcTemplate.update(sql, namedParameters, keyHolder, new String[]{"id"});
	}

	private static RowMapper<Card> rowMapper = (rs, rowNum) -> Card.of(rs.getLong("id"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getLong("member_id"),
				rs.getTimestamp("modified_at").toLocalDateTime(),
				rs.getInt("card_section_code_id"));
}
