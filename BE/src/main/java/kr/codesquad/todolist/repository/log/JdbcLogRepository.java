package kr.codesquad.todolist.repository.log;

import kr.codesquad.todolist.domain.Activity;
import kr.codesquad.todolist.domain.Log;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcLogRepository implements LogRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcLogRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public boolean save(Log log) {
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(log);
        String sql = "insert into log (member_id, previous_subject, current_subject, previous_section_id, current_section_id, activated_at, activity)" +
                "values (:memberId, :previousSubject, :currentSubject, :previousSectionId, :currentSectionId, :activatedAt, :activityString)";
        return namedParameterJdbcTemplate.update(sql, beanPropertySqlParameterSource) == 1;
    }

    @Override
    public List<Log> findAll() {
        String sql = "select id, member_id, previous_subject, current_subject, previous_section_id, current_section_id, activated_at, activity from log";
        return namedParameterJdbcTemplate.query(sql, logRowMapper());
    }

    private RowMapper<Log> logRowMapper() {
        return (rs, count) ->
                new Log(
                        rs.getLong("id"),
                        rs.getString("member_id"),
                        rs.getString("previous_subject"),
                        rs.getString("current_subject"),
                        rs.getInt("previous_section_id"),
                        rs.getInt("current_section_id"),
                        rs.getTimestamp("activated_at").toLocalDateTime(),
                        Activity.valueOf(rs.getString("activity")));
    }
}

