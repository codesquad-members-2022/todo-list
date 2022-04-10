package com.codesquad.todolist.column;

public class Column {

    private Integer columnId;
    private Integer userId;
    private String columnName;
    private Boolean deleted;

    public Column(Integer userId, String columnName) {
        this.userId = userId;
        this.columnName = columnName;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }
}
