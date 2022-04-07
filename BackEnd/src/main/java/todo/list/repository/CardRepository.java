package todo.list.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import todo.list.domain.Card;

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CardRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Card card) {

    }
}
