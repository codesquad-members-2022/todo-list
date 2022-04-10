package com.hooria.todo.domain;

import com.hooria.todo.dto.CardResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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

    public static Card of(Status status, String title, String content, String userId, Device device, int rowPosition) {
        return new Card(0, status, title, content, userId, device, LocalDateTime.now(), LocalDateTime.now(), false, rowPosition);
    }

    public static Card of(String status, String title, String content, String userId, String device, int rowPosition) {
        return new Card(0, Status.of(status), title, content, userId, Device.of(device), LocalDateTime.now(), LocalDateTime.now(), false, rowPosition);
    }

    public CardResponse toCardResponse() {
        return new CardResponse(
            id,
            status.name(),
            title,
            content,
            userId,
            device.name(),
            createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            modifiedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            deletedYn,
            rowPosition
        );
    }
}
