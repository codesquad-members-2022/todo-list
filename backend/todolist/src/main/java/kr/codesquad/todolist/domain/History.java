package kr.codesquad.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class History {

    private Long id;
    private Action action;
    private String cardSubject;
    private Card.TodoStatus prevCardStatus;
    private Card.TodoStatus currentCardStatus;
    private LocalDateTime createdAt;
    private Long userId;
    private Long cardId;

    public enum Action {
        CREATE,
        UPDATE,
        DELETE,
        MOVE
    }

    public static HistoryBuilder buildWith(Action action, Card card) {
        return builder()
                .action(action)
                .cardSubject(card.getSubject())
                .prevCardStatus(card.getStatus())
                .currentCardStatus(card.getStatus())
                .createdAt(LocalDateTime.now())
                .userId(card.getUserId())
                .cardId(card.getCardId());
    }
}
