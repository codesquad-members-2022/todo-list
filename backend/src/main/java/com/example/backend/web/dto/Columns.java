package com.example.backend.web.dto;

import java.util.ArrayList;
import java.util.List;

public class Columns {
    private final List<Column> columns;

    public Columns() {
        columns = new ArrayList<>();
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public List<Column> getColumns() {
        return columns;
    }
}
