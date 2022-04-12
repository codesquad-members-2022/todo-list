package com.codesquad.todolist.history;

import com.codesquad.todolist.history.domain.Field;
import com.codesquad.todolist.history.domain.ModifiedField;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ModifiedFieldRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ModifiedFieldRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createAll(List<ModifiedField> modifiedFields) {
        String sql = "insert into modified_field (history_id, field, old_value, new_value) values (:historyId, :field, :oldValue, :newValue)";

        MapSqlParameterSource[] batch = new MapSqlParameterSource[modifiedFields.size()];

        for (int ind = 0; ind < modifiedFields.size(); ind++) {
            ModifiedField modifiedField = modifiedFields.get(ind);
            MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("historyId", modifiedField.getHistoryId())
                .addValue("field", modifiedField.getField().toString())
                .addValue("oldValue", modifiedField.getOldValue())
                .addValue("newValue", modifiedField.getNewValue());
            batch[ind] = param;
        }

        jdbcTemplate.batchUpdate(sql, batch);
    }

    public List<ModifiedField> findByHistoryIds(List<Integer> historyIds) {
        String sql = "select modified_field_id, history_id, field,"
            + " (case when field = 'COLUMN' then (select column_name from `column` where column_id = old_value) else old_value end) as old_value,"
            + " (case when field = 'COLUMN' then (select column_name from `column` where column_id = new_value) else new_value end) as new_value"
            + " from modified_field"
            + " where history_id in (:historyIds)";

        MapSqlParameterSource source = new MapSqlParameterSource()
            .addValue("historyIds", historyIds);

        return jdbcTemplate.query(sql, source, getRowMapper());
    }

    private RowMapper<ModifiedField> getRowMapper() {
        return (rs, rowNum) -> new ModifiedField(
            rs.getInt("modified_field_id"),
            rs.getInt("history_id"),
            Field.valueOf(rs.getString("field")),
            rs.getString("old_value"),
            rs.getString("new_value")
        );
    }
}
