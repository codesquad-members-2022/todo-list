package com.example.backend.controller.card;

import java.util.List;

public class DailyPlan {

    private List<TodoItem> todoItems;
    private List<HaveDoneItem> haveDoneItems;
    private List<DoingItem> doingItems;

    public DailyPlan (){};

    public DailyPlan(List<TodoItem> todoItems, List<HaveDoneItem> haveDoneItems, List<DoingItem> doingItems) {
        this.todoItems = todoItems;
        this.haveDoneItems = haveDoneItems;
        this.doingItems = doingItems;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public List<HaveDoneItem> getHaveDoneItems() {
        return haveDoneItems;
    }

    public List<DoingItem> getDoingItems() {
        return doingItems;
    }
}
