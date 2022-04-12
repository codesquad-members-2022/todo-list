package com.todolist.project.domain.log;

import com.todolist.project.domain.ActionStatus;
import com.todolist.project.domain.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Log {
    private Long id;
    private String title;
    private CardStatus prevStatus;
    private CardStatus currentStatus;
    private ActionStatus actionStatus;
    private LocalDateTime actionTime;

    public Log(String title, CardStatus prevStatus, CardStatus currentStatus, ActionStatus actionStatus) {
        this.title = title;
        this.prevStatus = prevStatus;
        this.currentStatus = currentStatus;
        this.actionStatus = actionStatus;
        this.actionTime = LocalDateTime.now();
    }
}
