package team03.todoapp.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team03.todoapp.domain.History;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class HistoryRepository {

    private Logger log = LoggerFactory.getLogger(HistoryRepository.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public HistoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<History> findAll() {
        String selectAllHistories = "select history_id, action_type, card_title, past_location, now_location, history_date from history";
        List<History> histories = null;

        try {
            histories = jdbcTemplate.query(selectAllHistories, historyRowMapper());
        } catch(EmptyResultDataAccessException e) { // 결과가 empty이면 exception 터지므로 null값을 유지
            log.debug("history table empty!");
        }
        return histories;
    }

    private RowMapper<History> historyRowMapper() {
        return (rs, count) ->
            new History(
                    rs.getLong("history_id"),
                    rs.getString("action_type"),
                    rs.getString("card_title"),
                    rs.getString("past_location"),
                    rs.getString("now_location"),
                    LocalDateTime.parse(rs.getString("history_date"), formatter)
            );

    }
}
