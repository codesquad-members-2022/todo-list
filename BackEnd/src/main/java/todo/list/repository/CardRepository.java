package todo.list.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import todo.list.domain.Author;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;

import java.util.List;

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CardRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Card card) {

    }

    public List<Card> findAll() {
        return jdbcTemplate.query("Select id, title, contents, card_status, author from card order by create_date desc", cardsRowMapper());
    }

    private RowMapper<Card> cardsRowMapper() {
        return (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            String contents = rs.getString("contents");
            CardStatus cardStatus = CardStatus.valueOf(rs.getString("card_status"));
            Author author = Author.valueOf(rs.getString("author"));
            return new Card(id, title, contents, cardStatus, author);
        };
    }
}
