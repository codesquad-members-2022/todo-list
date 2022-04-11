package com.todolist.project.domain.card;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardUpdateDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Repository
public class CardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Card> rowMapper;

    public CardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = (rs, rowNum) -> {
                String status = rs.getString("card_Status");
                return new Card(
                        rs.getLong("id"),
                        rs.getInt("card_index"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("writer"),
                        rs.getTimestamp("created_date").toLocalDateTime(),
                        Enum.valueOf(CardStatus.class, status)
                );
        };
    }


    private final static String DELETE_CARD_SQL = "DELETE FROM card WHERE id = ?";
    private final static String INSERT_CARD_SQL = "INSERT INTO card(card_index, title, contents, writer, created_date, card_status) VALUES (?,?,?,?,?,?)";
    private final static String FIND_CARD_SQL = "SELECT id, card_index, title, contents, writer, card_Status, created_date FROM card ORDER BY card_index ASC";
    private final static String UPDATE_CARD_SQL = "UPDATE card SET card_index = ?, title = ?, contents = ?, card_status = ?, created_date = ? WHERE id = ?";
    private final static String FIND_ID_SQL = "SELECT id, card_index, title, contents, writer, card_status, created_date FROM card WHERE id = ?";

    public Optional<Card> findCardById(Long id) {
        return jdbcTemplate.query(FIND_ID_SQL, rowMapper, id).stream()
                .findAny();
    }

    public List<Card> findAll() {
        return jdbcTemplate.query(FIND_CARD_SQL, rowMapper);
    }

    public int add(CardAddDto cardAddDto, int size){
        return jdbcTemplate.update(INSERT_CARD_SQL, ++size, cardAddDto.getTitle(),
                cardAddDto.getContents(), cardAddDto.getWriter(), cardAddDto.cardCreatedTime(), cardAddDto.createCardStatus().name());
    }

    public int remove(Long id){
        return jdbcTemplate.update(DELETE_CARD_SQL, id);
    }

    public int update(Long id, CardUpdateDto dto) {
        return jdbcTemplate.update(UPDATE_CARD_SQL, dto.getCardIndex(), dto.getTitle(), dto.getContents(), dto.getCardStatus().name(), dto.updateCardCreatedTime(), id);
    }
}
