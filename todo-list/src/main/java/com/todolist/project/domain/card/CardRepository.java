package com.todolist.project.domain.card;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.web.dto.CardUpdateDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@Repository
public class CardRepository {

    private final JdbcTemplate jdbcTemplate;
    public CardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private final static String DELETE_CARD_SQL = "DELETE FROM card WHERE id = ?";
    private final static String INSERT_CARD_SQL = "INSERT INTO card(title, contents, writer, created_date, card_status) VALUE (?,?,?,?,?)";
    private final static String FIND_CARD_SQL = "SELECT id, title, contents, writer, card_Status, created_date FROM card";
    private final static String UPDATE_CARD_SQL = "UPDATE card SET title = ?, contents = ?, card_status = ?, created_date = ? WHERE id = ?";
    private final static String FIND_ID_SQL = "SELECT id, title, contents, writer, card_status, created_date FROM card WHERE id = ?";

    public Optional<Card> findCardById(Long id) {
        return jdbcTemplate.query(FIND_ID_SQL, cardRowMapper(), id).stream()
                .findAny();
    }


    //TODO: ID값만 반환 -> simpleJDBC
    public int add(Card card){
        return jdbcTemplate.update(INSERT_CARD_SQL, card.getTitle(), card.getContents(), card.getWriter(), card.getCreatedTime(), card.getCardStatus());
    }

    public int remove(Long id){
        return jdbcTemplate.update(DELETE_CARD_SQL, id);
    }

    public List<Card> findAll() { return jdbcTemplate.query(FIND_CARD_SQL, cardRowMapper()); }

    public int update(Long id, CardUpdateDto dto) {
        return jdbcTemplate.update(UPDATE_CARD_SQL, dto.getTitle(), dto.getContents(), dto.getCardStatus(), dto.updateCardCreatedTime(), id);
    }

    private RowMapper<Card> cardRowMapper(){
        return (rs, rowNum) -> {
            String status = rs.getString("card_Status");
            Card card = new Card(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getString("writer"),
                    rs.getTimestamp("created_date").toLocalDateTime(),
                    Enum.valueOf(CardStatus.class, status)
            );
            return card;
        };
    }
}
