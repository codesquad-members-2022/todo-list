package com.ijava.todolist.history.domain;

import com.ijava.todolist.history.Action;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class History {

    private Long id;
    private final Long userId;
    private final Long cardId;
    private final Long oldColumnsId;
    private final Long newColumnsId;
    private final Action action;
    private final LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public History(@NonNull Long userId, @NonNull Long cardId, @NonNull Long oldColumnsId, @NonNull Long newColumnsId,
        @NonNull Action action, @NonNull LocalDateTime createdDate) {
        this.userId = userId;
        this.cardId = cardId;
        this.oldColumnsId = oldColumnsId;
        this.newColumnsId = newColumnsId;
        this.action = action;
        this.createdDate = createdDate;
        this.modifiedDate = createdDate;
    }

    public History(Long id, @NonNull Long userId, @NonNull Long cardId, @NonNull Long oldColumnsId, @NonNull Long newColumnsId,
        @NonNull Action action, @NonNull LocalDateTime createdDate,
        @NonNull LocalDateTime modifiedDate) {
        this(userId, cardId, oldColumnsId, newColumnsId, action, createdDate);
        this.id = id;
        this.modifiedDate = modifiedDate;
    }
}
