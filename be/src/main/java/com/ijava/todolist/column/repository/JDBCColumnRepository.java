package com.ijava.todolist.column.repository;

import com.ijava.todolist.column.domain.Column;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCColumnRepository implements ColumnRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCColumnRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<List<Column>> findAll() {
        String findAllSql = "SELECT id,name,created_date,modified_date FROM columns";
        List<Column> columnList = jdbcTemplate.query(findAllSql, columnRowMapper());
        return columnList.isEmpty() ? Optional.empty() : Optional.of(columnList);
    }

    private RowMapper<Column> columnRowMapper() {
        return (rs, rowNum) -> Column.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
            .modifiedDate(rs.getTimestamp("modified_date").toLocalDateTime())
            .build();
    }
}
