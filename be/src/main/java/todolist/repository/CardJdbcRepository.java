package todolist.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import todolist.domain.card.Card;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CardJdbcRepository implements CardRepository<Card> {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    private RowMapper<Card> cardRowMapper = (rs, rowNum) -> {
        Card card = new Card(
                rs.getString("section"),
                rs.getString("title"),
                rs.getString("content"));
        card.setId(rs.getLong("id"));
        return card;
    };


    public CardJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        this.jdbcInsert.withTableName("todos")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Card save(Card card) {
        Map<String, String> params = new HashMap<>();
        params.put("section", card.getSection());
        params.put("title", card.getTitle());
        params.put("content", card.getContent());

        long id = jdbcInsert.executeAndReturnKey(params).longValue();
        card.setId(id);
        return card;
    }

    @Override
    public Card findById(Long id) {
        List<Card> result = jdbcTemplate.query("select id, section, title, content from todos where id = ? ", cardRowMapper, id);
        return result.stream()
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public List<Card> findAll() {
        List<Card> result = jdbcTemplate.query("select id, section, title, content from todos ", cardRowMapper);
        return result;
    }

    @Override
    public void update(Card card) {
        String updateQuery = "update todos set section = ?, title = ?, content = ? where id = ?";
        jdbcTemplate.update(updateQuery, card.getSection(), card.getTitle(), card.getContent(), card.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from todos where id = ?", id);
    }
}
