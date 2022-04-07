package com.hooria.todo.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {

    private long id;
    private Status status;
    private String title;
    private String content;
    private String userId;
    private Device device;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean deletedYn;
    private int rowPosition;

    public static Card of(Status status, String title, String content, String userId, Device device, int index) {
        return new Card(0, status, title, content, userId, device, LocalDateTime.now(), LocalDateTime.now(), false, index);
    }

    public static Card of(int statusCode, String title, String content, String userId, int deviceCode, int index) {
        return new Card(0, Status.of(statusCode), title, content, userId, Device.of(deviceCode), LocalDateTime.now(), LocalDateTime.now(), false, index);
    }
}
