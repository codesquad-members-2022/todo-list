package com.hooria.todo.repository;

import com.hooria.todo.domain.Card;
import com.hooria.todo.domain.Device;
import com.hooria.todo.domain.Status;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository {

    private final SimpleJdbcInsert insertJdbc;
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Card> cardRowMapper;

    public CardRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
        insertJdbc = new SimpleJdbcInsert(dataSource)
            .withTableName("task_card")
            .usingGeneratedKeyColumns("id");

        cardRowMapper = (rs, row) ->
            new Card(
                rs.getLong("id"),
                Status.of(rs.getInt("status")),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("user_id"),
                Device.of(rs.getInt("device")),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getObject("modified_at", LocalDateTime.class),
                rs.getBoolean("deleted_yn"),
                rs.getInt("row_position")
            );
    }

    public List<Card> findAll() {
        return jdbc.query(
            "select id, status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position "
                + "from task_card where deleted_yn = false",
            Collections.emptyMap(), cardRowMapper
        );
    }

    public Optional<Card> findById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        Card card = DataAccessUtils.singleResult(jdbc.query(
            "select id, status, title, content, user_id, device, created_at, modified_at, deleted_yn, row_position "
                + "from task_card where id = :id",
            namedParameters, cardRowMapper));

        return Optional.ofNullable(card);
    }

    public int countByStatus(int status) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", status);
        return jdbc.queryForObject(
            "select count(*) as count from task_card where status = :status and deleted_yn = false",
            namedParameters, Integer.class
        );
    }

    public long add(Card card) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", card.getId());
        parameters.put("status", card.getStatus().getCode());
        parameters.put("title", card.getTitle());
        parameters.put("content", card.getContent());
        parameters.put("user_id", card.getUserId());
        parameters.put("device", card.getDevice().getCode());
        parameters.put("created_at", card.getCreatedAt());
        parameters.put("modified_at", card.getModifiedAt());
        parameters.put("deleted_yn", card.isDeletedYn());
        parameters.put("row_position", card.getRowPosition());

        return (long) insertJdbc.executeAndReturnKey(parameters);
    }

    public Card update(Card card) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("id", card.getId())
            .addValue("status", card.getStatus().getCode())
            .addValue("title", card.getTitle())
            .addValue("content", card.getContent())
            .addValue("user_id", card.getUserId())
            .addValue("device", card.getDevice().getCode())
            .addValue("modified_at", card.getModifiedAt())
            .addValue("deleted_yn", card.isDeletedYn())
            .addValue("row_position", card.getRowPosition());

        jdbc.update(
            "update task_card set status = :status, title = :title, content = :content, user_id = :user_id, "
                + "device = :device, modified_at = :modified_at, deleted_yn = :deleted_yn, row_position = :row_position "
                + "where id = :id", namedParameters);

        return card;
    }

    public long delete(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        jdbc.update("update task_card set deleted_yn = true where id = :id", namedParameters);

        return id;
    }
}
