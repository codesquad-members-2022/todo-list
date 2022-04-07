package com.todolist.domain;

public class Category {

    private Integer id;
    private String name;

    private Category() { }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
