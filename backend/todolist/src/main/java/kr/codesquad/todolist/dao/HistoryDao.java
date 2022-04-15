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

    private static final String HISTORY_TABLE_NAME = "todo_history_table";
    private static final String HISTORY_PRIMARY_KEY = "id";
    private static final String HISTORY_ACTION = "action";
    private static final String HISTORY_CARD_SUBJECT = "cardSubject";
    private static final String HISTORY_PREV_CARD_STATUS = "prevCardStatus";
    private static final String HISTORY_CURRENT_CARD_STATUS = "currentCardStatus";
    private static final String HISTORY_CREATED_AT = "createdAt";
    private static final String HISTORY_USER_ID = "userId";
    private static final String HISTORY_CARD_ID = "cardId";

    private final JdbcTemplate jdbcTemplate;

    public void save(History history) {
        new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(HISTORY_TABLE_NAME)
                .usingGeneratedKeyColumns(HISTORY_PRIMARY_KEY)
                .execute(new BeanPropertySqlParameterSource(history));
    }

    public List<History> findAllByUserId(Long userId) {
        String sql = String.format("SELECT %1$s, %2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s FROM %9$s WHERE %7$s = ?;",
                HISTORY_PRIMARY_KEY,
                HISTORY_ACTION,
                HISTORY_CARD_SUBJECT,
                HISTORY_PREV_CARD_STATUS,
                HISTORY_CURRENT_CARD_STATUS,
                HISTORY_CREATED_AT,
                HISTORY_USER_ID,
                HISTORY_CARD_ID,
                HISTORY_TABLE_NAME);

        return jdbcTemplate.query(sql, historyRowMapper(), userId);
    }

    private RowMapper<History> historyRowMapper() {
        return (rs, rowNum) ->
                new History(rs.getLong(HISTORY_PRIMARY_KEY),
                        History.Action.valueOf(rs.getString(HISTORY_ACTION)),
                        rs.getString(HISTORY_CARD_SUBJECT),
                        Card.TodoStatus.valueOf(rs.getString(HISTORY_PREV_CARD_STATUS)),
                        Card.TodoStatus.valueOf(rs.getString(HISTORY_CURRENT_CARD_STATUS)),
                        rs.getTimestamp(HISTORY_CREATED_AT).toLocalDateTime(),
                        rs.getLong(HISTORY_USER_ID),
                        rs.getLong(HISTORY_CARD_ID));
    }
}
