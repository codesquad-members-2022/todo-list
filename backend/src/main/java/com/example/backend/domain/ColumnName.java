package com.example.backend.domain;

public enum ColumnName {
    TO_DO("to_do"), IN_PROGRESS("in_progress"), DONE("done");

    private final String name;

    ColumnName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
