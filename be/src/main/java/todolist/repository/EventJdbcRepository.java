package todolist.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import todolist.domain.event.Action;
import todolist.domain.event.Event;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventJdbcRepository implements EventRepository{

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    private RowMapper<Event> eventRowMapper = (rs, rowNum) ->
            new Event(
                    rs.getLong("id"),
                    rs.getString("cardTitle"),
                    rs.getString("prevSection"),
                    rs.getString("currentSection"),
                    Action.valueOf(rs.getString("action")),
                    rs.getTimestamp("createdAt").toLocalDateTime()
            );
    
    public EventJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        this.jdbcInsert.withTableName("events")
                .usingGeneratedKeyColumns("id")
                .usingGeneratedKeyColumns("createdAt");
    }

    public void save(Event event) {
        Map<String, Object> params = new HashMap<>();
        params.put("cardTitle", event.getCardTitle());
        params.put("prevSection", event.getPrevSection());
        params.put("currentSection", event.getCurrentSection());
        params.put("action", event.getAction());

        jdbcInsert.execute(params);
    }

    public List<Event> findAll() {
        List<Event> events = jdbcTemplate.query("select id,cardTitle,prevSection,currentSection,action,createdAt from events", eventRowMapper);
        return events;
    }
}
