package team03.todoapp.controller.dto;

import javax.validation.constraints.NotNull;
import team03.todoapp.domain.Card;
import java.time.LocalDateTime;

public class CardAddFormRequest {

    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String writer;
    @NotNull
    private String currentLocation;

    public CardAddFormRequest(String title, String content, String writer, String currentLocation) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.currentLocation = currentLocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Card toEntity() {
        return new Card(
            this.title,
            this.content,
            this.writer,
            this.currentLocation,
            LocalDateTime.now(),
            null);
    }
}
