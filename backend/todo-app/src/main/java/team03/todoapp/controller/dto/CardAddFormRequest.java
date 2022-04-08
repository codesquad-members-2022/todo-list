package team03.todoapp.controller.dto;


import org.springframework.lang.NonNull;
import team03.todoapp.domain.Card;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CardAddFormRequest {

    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private String writer;
    @NonNull
    private String currentLocation;

    public CardAddFormRequest() {
    }

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


    public Card toCardEntity() {
        Card card = new Card(
                this.title,
                this.content,
                this.writer,
                this.currentLocation,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                null,
                "0");

        return card;
    }
}
