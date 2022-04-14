package kr.codesquad.todolist.dao;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.domain.History;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class HistoryDao {

    private static final String TABLE_NAME = "history";
    private static final String PRIMARY_KEY_COLUMN_NAME = "id";
    private static final String ACTION_COLUMN_NAME = "action";
    private static final String CARD_SUBJECT_COLUMN_NAME = "cardSubject";
    private static final String PREV_CARD_STATUS_COLUMN_NAME = "prevCardStatus";
    private static final String CURRENT_CARD_STATUS_COLUMN_NAME = "currentCardStatus";
    private static final String CREATED_AT_COLUMN_NAME = "createdAt";
    private static final String USER_ID_COLUMN_NAME = "userId";
    private static final String CARD_ID_COLUMN_NAME = "cardId";

    private final JdbcTemplate jdbcTemplate;

    public void save(History history) {
        new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(PRIMARY_KEY_COLUMN_NAME)
                .execute(new BeanPropertySqlParameterSource(history));
    }

    public List<History> findAllByUserId(Long userId) {
        String sql = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s = ?;",
                PRIMARY_KEY_COLUMN_NAME,
                ACTION_COLUMN_NAME,
                CARD_SUBJECT_COLUMN_NAME,
                PREV_CARD_STATUS_COLUMN_NAME,
                CURRENT_CARD_STATUS_COLUMN_NAME,
                CREATED_AT_COLUMN_NAME,
                USER_ID_COLUMN_NAME,
                CARD_ID_COLUMN_NAME,
                TABLE_NAME,
                USER_ID_COLUMN_NAME);

        return jdbcTemplate.query(sql, historyRowMapper(), userId);
    }

    private RowMapper<History> historyRowMapper() {
        return (rs, rowNum) ->
                new History(rs.getLong(PRIMARY_KEY_COLUMN_NAME),
                        History.Action.valueOf(rs.getString(ACTION_COLUMN_NAME)),
                        rs.getString(CARD_SUBJECT_COLUMN_NAME),
                        Card.TodoStatus.valueOf(rs.getString(PREV_CARD_STATUS_COLUMN_NAME)),
                        Card.TodoStatus.valueOf(rs.getString(CURRENT_CARD_STATUS_COLUMN_NAME)),
                        rs.getTimestamp(CREATED_AT_COLUMN_NAME).toLocalDateTime(),
                        rs.getLong(USER_ID_COLUMN_NAME),
                        rs.getLong(CARD_ID_COLUMN_NAME));
    }
}
