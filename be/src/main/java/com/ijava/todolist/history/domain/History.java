package com.ijava.todolist.history.domain;

import com.ijava.todolist.history.Action;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class History {

    private Long id;
    private final Long userId;
    private final Long cardId;
    private final Long columnsId;
    private final Action action;
    private final LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public History(@NonNull Long userId, @NonNull Long cardId, @NonNull Long columnsId,
        @NonNull Action action, @NonNull LocalDateTime createdDate) {
        this.userId = userId;
        this.cardId = cardId;
        this.columnsId = columnsId;
        this.action = action;
        this.createdDate = createdDate;
        this.modifiedDate = createdDate;
    }

    public History(Long id, @NonNull Long userId, @NonNull Long cardId, @NonNull Long columnsId,
        @NonNull Action action, @NonNull LocalDateTime createdDate,
        @NonNull LocalDateTime modifiedDate) {
        this(userId, cardId, columnsId, action, createdDate);
        this.id = id;
        this.modifiedDate = modifiedDate;
    }
}
