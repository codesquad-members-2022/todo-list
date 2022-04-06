package com.hooria.todo.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {

    private long id;
    private int status;
    private String title;
    private String content;
    private String userId;
    private int applianceInfo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean deletedYn;
    private int index;

    public static Card of(int status, String title, String content, String userId, int applianceInfo, int index) {
        return new Card(0, status, title, content, userId, applianceInfo, LocalDateTime.now(), LocalDateTime.now(), false, index);
    }
}
