package com.hooria.todo.domain;

import com.hooria.todo.dto.CardResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
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

    public static Card of(long id, String status, String title, String content, String userId, String device) {
        LocalDateTime now = LocalDateTime.now();
        return new Card(id, Status.of(status), title, content, userId, Device.of(device), now, now, false, 0);
    }

    public static Card of(Status status, String title, String content, String userId, Device device, int rowPosition) {
        LocalDateTime now = LocalDateTime.now();
        return new Card(0, status, title, content, userId, device, now, now, false, rowPosition);
    }

    public static Card of(String status, String title, String content, String userId, String device, int rowPosition) {
        LocalDateTime now = LocalDateTime.now();
        return new Card(0, Status.of(status), title, content, userId, Device.of(device), now, now, false, rowPosition);
    }

    public CardResponse toCardResponse() {
        return new CardResponse(
            id,
            status.name(),
            title,
            content,
            userId,
            device.name(),
            createdAt.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            modifiedAt.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            deletedYn,
            rowPosition
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return id == card.id && deletedYn == card.deletedYn && rowPosition == card.rowPosition
            && status == card.status && Objects.equals(title, card.title)
            && Objects.equals(content, card.content) && Objects.equals(userId,
            card.userId) && device == card.device && Objects.equals(createdAt, card.createdAt)
            && Objects.equals(modifiedAt, card.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, title, content, userId, device, createdAt, modifiedAt,
            deletedYn, rowPosition);
    }
}
