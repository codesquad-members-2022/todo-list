package kr.codesquad.todolist.repository;

import kr.codesquad.todolist.domain.Card;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JdbcCardRepository implements CardRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcCardRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Card save(Card card) {
        String sql = "insert into card (user_id, column_id, subject, contents, create_time, update_time) " +
                "values (:userId, :columnId, :subject, :contents, :createTime, :updateTime)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card), keyHolder);
        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return Card.instanceWithId(id, card);
    }

    @Override
    public Optional<Card> findById(Long id) {
        String sql = "select id, user_id, column_id, subject, contents, create_time, update_time, deleted " +
                "from card where id = :id and deleted = false";
        try {
            Card card = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), cardRowMapper());
            return Optional.ofNullable(card);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Card> findAll() {
        String sql = "select id, user_id, column_id, subject, contents, create_time, update_time, deleted from card where deleted = false";
        return namedParameterJdbcTemplate.query(sql, cardRowMapper());
    }

    @Override
    public boolean delete(Long id) {
        String sql = "update card set deleted = true where id = :id";

        return namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id)) == 1;
    }


    private RowMapper<Card> cardRowMapper() {
        return (rs, rowNum) -> Card.of(rs.getLong("id"),
                rs.getString("user_id"),
                rs.getInt("column_id"),
                rs.getString("subject"),
                rs.getString("contents"),
                rs.getObject("create_time", LocalDateTime.class),
                rs.getObject("update_time", LocalDateTime.class),
                rs.getBoolean("deleted"));
    }
}
