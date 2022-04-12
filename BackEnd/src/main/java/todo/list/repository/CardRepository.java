package todo.list.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import todo.list.domain.Author;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CardRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Card card) {
        String sql = "insert into card" +
                "(title, contents, card_status, author, update_datetime) values" +
                "(:title, :contents, :card_status, :author, :update_datetime)";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("title", card.getTitle())
                .addValue("contents", card.getContents())
                .addValue("card_status", card.getStatus().name())
                .addValue("author", card.getAuthor().name())
                .addValue("update_datetime", card.getUpdateDateTime());

        jdbcTemplate.update(sql, namedParameters);
    }

    public List<Card> findAll() {
        return jdbcTemplate.query("Select id, title, contents, card_status, update_datetime, author from card order by update_datetime desc", cardsRowMapper());
    }

    private RowMapper<Card> cardsRowMapper() {
        return (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            String contents = rs.getString("contents");
            CardStatus cardStatus = CardStatus.valueOf(rs.getString("card_status"));
            LocalDateTime updateDatetime = rs.getTimestamp("update_datetime").toLocalDateTime();
            Author author = Author.valueOf(rs.getString("author"));
            return new Card(id, title, contents, cardStatus, updateDatetime, author);
        };
    }

    public void update(Card card) {
        String updateSql = "UPDATE card SET title=:title, contents=:contents, author=:author, update_datetime=:update_datetime WHERE id=:id";
        Map<String,Object> params = new HashMap<>();

        params.put("title", card.getTitle());
        params.put("contents", card.getContents());
        params.put("author", card.getAuthor().name());
        params.put("update_datetime", card.getUpdateDateTime());
        params.put("id", card.getId());

        jdbcTemplate.update(updateSql, params);
    }
}
