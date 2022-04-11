package com.team15.todoapi.repository;

import com.team15.todoapi.domain.Member;
import java.util.Collections;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMemberRepository implements MemberRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcMemberRepository(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public Member findByUserId(String userId) {
		Map namedParameters = Collections.singletonMap("userId", userId);
		String sql = "SELECT id,"
			+ "user_id, "
			+ "name "
			+ "FROM MEMBER "
			+ "where user_id = :userId";
		return jdbcTemplate.queryForObject(sql, namedParameters, rowMapper);
	}

	private static RowMapper<Member> rowMapper =
		(rs, rowNum) -> Member.of(rs.getLong("id"),
			rs.getString("user_id"),
			rs.getString("name"));

}
