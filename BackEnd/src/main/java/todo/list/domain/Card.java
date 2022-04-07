package todo.list.domain;

import java.time.LocalDateTime;

public class Card {
    private Long id;
    private String title;
    private String contents;
    private CardStatus status;
    private LocalDateTime createDateTime;
}
