package team03.todoapp.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import team03.todoapp.controller.CardLocation;
import team03.todoapp.repository.domain.History;

@Repository
public class HistoryRepository {

    private Logger log = LoggerFactory.getLogger(HistoryRepository.class);
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private RowMapper<History> historyRowMapper = (rs, count) ->
        new History(
            rs.getLong("history_id"),
            rs.getString("action_type"),
            rs.getString("card_title"),
            CardLocation.valueOf(rs.getString("past_location")),
            CardLocation.valueOf(rs.getString("now_location")),
            rs.getObject("history_date", LocalDateTime.class)
        );

    @Autowired
    public HistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("history")
            .usingGeneratedKeyColumns("history_id");
    }

    public Long insert(History history) {
        Map<String, Object> params = new HashMap<>();
        params.put("action_type", history.getActionType());
        params.put("card_title", history.getCardTitle());
        params.put("past_location", history.getPastLocation().name());
        params.put("now_location", history.getNowLocation().name());
        params.put("history_date", history.getHistoryDateTime());
        return simpleJdbcInsert.executeAndReturnKey(params).longValue();
    }

    public List<History> findAll() {
        String selectAllHistories = "select history_id, action_type, card_title, past_location, now_location, history_date from history";
        List<History> histories = null;

        try {
            histories = jdbcTemplate.query(selectAllHistories, historyRowMapper);
        } catch (EmptyResultDataAccessException e) { // 결과가 empty이면 exception 터지므로 null값을 유지
            log.debug("history table empty!");
        }
        return histories;
    }
}
