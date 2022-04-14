package codesquad.todo.domain.work;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Primary
@Repository
public class JdbcTemplateWorkRepository implements WorkRepository{

    private NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateWorkRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Work save(Work work) {
        String SQL = "INSERT INTO works (title, content, author_id, work_status, status_index, create_date_time, last_modified_date_time, deleted) " +
                "VALUES (:title, :content, :author, :workStatus, :statusIndex, :createDateTime, :lastModifiedDateTime, :isDeleted)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("title", work.getTitle())
                .addValue("content", work.getContent())
                .addValue("author", work.getAuthor().getId())
                .addValue("workStatus", work.getWorkStatus().name())
                .addValue("statusIndex", work.getStatusIndex())
                .addValue("createDateTime", work.getCreateDateTime())
                .addValue("lastModifiedDateTime", work.getLastModifiedDateTime())
                .addValue("isDeleted", work.isDeleted());
        jdbcTemplate.update(SQL, namedParameters, keyHolder);
        work.initId(keyHolder.getKey().longValue());
        return work;
    }

    @Override
    public Optional<Work> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Work updateWork) {

    }

    @Override
    public List<Work> findByUserIdAndStatus(WorkStatus workStatus, Long userId) {
        return null;
    }

    @Override
    public int countOfStatus(Long userId, WorkStatus workStatus) {
        return 0;
    }

    @Override
    public List<Work> findAllWorkByUserId(Long userId) {
        return null;
    }

    @Override
    public Optional<Work> findOne(Long userId, WorkStatus workStatus, Integer statusIndex) {
        return Optional.empty();
    }
}
