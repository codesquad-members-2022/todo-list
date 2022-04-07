package com.example.backend.controller.card.dto;

import java.util.List;

public class DailyPlan {

    private List<TodoItem> todoItems;
    private List<ProgressingItem> progressingItems;
    private List<CompletedItem> completedItems;
    private Author author;

    public DailyPlan (){};

    public DailyPlan(List<TodoItem> todoItems, List<ProgressingItem> progressingItems, List<CompletedItem> completedItems) {
        this.todoItems = todoItems;
        this.progressingItems = progressingItems;
        this.completedItems = completedItems;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public List<ProgressingItem> getProgressingItems() {
        return progressingItems;
    }

    public void setProgressingItems(List<ProgressingItem> progressingItems) {
        this.progressingItems = progressingItems;
    }

    public List<CompletedItem> getCompletedItems() {
        return completedItems;
    }

    public void setCompletedItems(List<CompletedItem> completedItems) {
        this.completedItems = completedItems;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
