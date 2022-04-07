package com.example.backend.controller.card.dto;

import java.util.ArrayList;
import java.util.List;

import static com.example.backend.domain.card.CardType.*;

public class DailyPlan {

    private List<TodoItem> todoItems;
    private List<ProgressingItem> progressingItems;
    private List<CompletedItem> completedItems;
    private Author author;

    public DailyPlan() {
    };

    public DailyPlan(List<Task> tasks) {
        this.todoItems = new ArrayList<>();
        this.progressingItems = new ArrayList<>();
        this.completedItems = new ArrayList<>();
        addTasks(tasks);
    }

    private void addTasks(List<Task> tasks) {
        for (Task task : tasks) {
            if (TODO.equals(task.getCardType())) {
                todoItems.add(new TodoItem(task));
            }
            if (COMPLETED.equals(task.getCardType())) {
                completedItems.add(new CompletedItem(task));
            }
            if (PROGRESSING.equals(task.getCardType())) {
                progressingItems.add(new ProgressingItem(task));
            }
        }
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public List<ProgressingItem> getProgressingItems() {
        return progressingItems;
    }

    public List<CompletedItem> getCompletedItems() {
        return completedItems;
    }

    public Author getAuthor() {
        return author;
    }

    public void acquire(Author author) {
        this.author = author;
    }
}
