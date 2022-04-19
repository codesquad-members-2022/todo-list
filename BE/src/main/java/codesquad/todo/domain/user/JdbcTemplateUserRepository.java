package codesquad.todo.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Slf4j
@Primary
@Repository
public class JdbcTemplateUserRepository implements UserRepository{

    private NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO users (name) VALUES (:name)";
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(SQL, namedParameters, keyHolder);
        Long userId = keyHolder.getKey().longValue();
        user.initId(userId);
        log.info("key : {}, user : {}", userId, user);
        return userId;
    }

    @Override
    public Optional<User> findById(Long id) {
        String SQL = "SELECT id, name FROM users WHERE id = :userId";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", id);
        return jdbcTemplate.query(SQL, namedParameters, userRowMapper()).stream()
                .findAny();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"));
    }
}
