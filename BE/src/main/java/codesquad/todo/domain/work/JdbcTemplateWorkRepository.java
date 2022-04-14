package codesquad.todo.domain.work;

import codesquad.todo.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
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
        String SQL = "SELECT w.id, w.title, w.content, w.author_id, w.work_status, w.status_index," +
                " w.create_date_time, w.last_modified_date_time, w.deleted, u.id, u.name " +
                "FROM works w JOIN users u " +
                "WHERE w.id = :id AND w.author_id = u.id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(SQL, namedParameters, workRowMapper()).stream().findAny();
    }

    private RowMapper<Work> workRowMapper() {
        return (rs, rowNum) -> Work.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .author(new User(rs.getLong("author_id"), rs.getString("name")))
                .workStatus(WorkStatus.valueOf(rs.getString("work_status")))
                .statusIndex((Integer) rs.getObject("status_index"))
                .createDateTime(rs.getTimestamp("create_date_time").toLocalDateTime())
                .lastModifiedDateTime(rs.getTimestamp("last_modified_date_time").toLocalDateTime())
                .isDeleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public void update(Work updateWork) {
        String SQL = "UPDATE works " +
                "SET title = :title, content = :content, work_status = :workStatus, status_index = :statusIndex,\n" +
                "last_modified_date_time = :lastModifiedDateTime, deleted = :deleted\n" +
                "WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", updateWork.getId())
                .addValue("title", updateWork.getTitle())
                .addValue("content", updateWork.getContent())
                .addValue("workStatus", updateWork.getWorkStatus().name())
                .addValue("statusIndex", updateWork.getStatusIndex())
                .addValue("lastModifiedDateTime", updateWork.getLastModifiedDateTime())
                .addValue("deleted", updateWork.isDeleted());
        jdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public List<Work> findByUserIdAndStatus(WorkStatus workStatus, Long userId) {
        String SQL = "SELECT w.id, w.title, w.content, w.author_id, w.work_status, w.status_index," +
                " w.create_date_time, w.last_modified_date_time, w.deleted, u.id, u.name " +
                "FROM works w JOIN users u " +
                "WHERE w.author_id = u.id AND w.work_status = :work_status AND w.author_id = :user_id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("work_status", workStatus.name())
                .addValue("user_id", userId);
        return jdbcTemplate.query(SQL, namedParameters, workRowMapper());
    }

    @Override
    public int countOfStatus(Long userId, WorkStatus workStatus) {
        String SQL = "SELECT COUNT(id) FROM works\n" +
                "WHERE work_status = :work_status AND author_id = :author_id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("work_status", workStatus.name())
                .addValue("author_id", userId);
        return jdbcTemplate.queryForObject(SQL, namedParameters, Integer.class);
    }

    @Override
    public List<Work> findAllWorkByUserId(Long userId) {
        String SQL = "SELECT w.id, w.title, w.content, w.author_id, w.work_status, w.status_index," +
                " w.create_date_time, w.last_modified_date_time, w.deleted, u.id, u.name " +
                "FROM works w JOIN users u " +
                "WHERE w.author_id = :user_id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("user_id", userId);
        return jdbcTemplate.query(SQL, namedParameters, workRowMapper());
    }

    @Override
    public Optional<Work> findOne(Long userId, WorkStatus workStatus, Integer statusIndex) {
        String SQL = "SELECT w.id, w.title, w.content, w.author_id, w.work_status, w.status_index," +
                " w.create_date_time, w.last_modified_date_time, w.deleted, u.id, u.name " +
                "FROM works w JOIN users u " +
                "WHERE w.author_id = u.id AND w.author_id = :userId AND w.work_status = :work_status AND w.status_index = :status_index";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", userId)
                                                        .addValue("work_status", workStatus.name())
                                                        .addValue("status_index", statusIndex);
        return jdbcTemplate.query(SQL, namedParameters, workRowMapper()).stream().findAny();
    }
}
