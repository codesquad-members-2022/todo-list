package com.ijava.todolist.history.repository.dto;

import com.ijava.todolist.history.Action;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class JoinedHistory {
    private Long id;
    private Long userId;
    private String userName;
    private Long cardId;
    private String cardTitle;
    private Long oldColumnsId;
    private String oldColumnName;
    private Long newColumnsId;
    private String newColumnName;
    private Action action;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
