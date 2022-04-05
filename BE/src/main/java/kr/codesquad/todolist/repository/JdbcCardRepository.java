package kr.codesquad.todolist.repository;

import kr.codesquad.todolist.domain.Card;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCardRepository implements CardRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcCardRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Card save(Card card) {
        return null;
    }

    @Override
    public Optional<Card> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
