package team03.todoapp.repository;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    @Autowired
    public CardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("card")
            .usingGeneratedKeyColumns("card_id");;
    }

    @Transactional
    public Long add() {
        /**
         *
         * select card_id from card where next_id = null and current_location = "done";
         * int lastCardId = cardId; if lastCardId==null; insert 후 return returnKey;
         *
         * insert한 후의 키값을 반환받아서 -> int thisCardId = returnKey;
         * card 테이블 done 속성 컬럼 중 마지막 컬럼의 next_id로 할당해줘야함
         *
         * update card
         * set next_id=thisCardId
         * where card_id=lastCardId;
         */

        String getLastCardIdSQL = "select card_id from card where next_id is null and current_location = 'done'";
        String updateNextCardOfLastCardSQL = "update card set next_id = ? where card_id = ?";
        Integer lastCardId;

        try {
            lastCardId = jdbcTemplate.queryForObject(getLastCardIdSQL, Integer.class);
            System.out.println("lastCardId = " + lastCardId);
        } catch(EmptyResultDataAccessException e) {
            lastCardId = null;
            System.out.println("e = " + e);
        }

        Map<String, String> params = new HashMap<>();
        params.put("title", "title1");
        params.put("content", "content1");
        params.put("writer", "writer1");
        params.put("current_location", "done");
        params.put("upload_date", "2020-02-02 12:00:00");
        params.put("deleted", "0");
        params.put("next_id", null);
        Long thisCardId = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        System.out.println("thisCardId = " + thisCardId);

        if (lastCardId != null) {
            jdbcTemplate.update(updateNextCardOfLastCardSQL, thisCardId, lastCardId);
        }
        return thisCardId;
    }



}
