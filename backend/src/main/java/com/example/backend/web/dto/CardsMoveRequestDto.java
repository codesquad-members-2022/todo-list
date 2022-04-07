package com.example.backend.web.dto;

public class CardsMoveRequestDto {
    private Long id;
    private Long currentIndex;
    private String currentStatus;
    private Long newIndex;
    private String newStatus;

    public CardsMoveRequestDto(Long id, Long currentIndex, String currentStatus, Long newIndex, String newStatus) {
        this.id = id;
        this.currentIndex = currentIndex;
        this.currentStatus = currentStatus;
        this.newIndex = newIndex;
        this.newStatus = newStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getCurrentIndex() {
        return currentIndex;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public Long getNewIndex() {
        return newIndex;
    }

    public String getNewStatus() {
        return newStatus;
    }
}
