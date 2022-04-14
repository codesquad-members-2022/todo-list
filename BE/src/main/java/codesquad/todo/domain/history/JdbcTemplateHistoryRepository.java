package codesquad.todo.domain.history;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Primary
@Repository
public class JdbcTemplateHistoryRepository implements HistoryRepository{

    private NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateHistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long save(History history) {
        String SQL = "INSERT INTO histories (work_id, history_type, history_time, before_status, after_status) " +
                "VALUES (:work_id, :history_type, :history_time, :before_status, :after_status)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("work_id", history.getWorkId())
                .addValue("history_type", history.getHistoryType())
                .addValue("history_time", history.getHistoryTime())
                .addValue("before_status", history.getBeforeStatus())
                .addValue("after_status", history.getAfterStatus());
        jdbcTemplate.update(SQL, namedParameters, keyHolder);
        history.initId(keyHolder.getKey().longValue());
        return history.getId();
    }

    @Override
    public List<History> findAll(Long userId) {
        return null;
    }
}
