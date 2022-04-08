package com.todolist.domain;

import lombok.Getter;

@Getter
public class Category {

    private Integer id;
    private String name;

    private Category() { }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
