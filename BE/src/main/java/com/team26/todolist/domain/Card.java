package com.team26.todolist.domain;

import com.team26.todolist.util.Constant;
import java.time.LocalDateTime;

public class Card implements Comparable<Card> {

    private Long id;
    private String title;
    private String contents;
    private String userId;
    private Long columnId;
    private Double order;
    private boolean isDeleted;
    private LocalDateTime createdAt;

    public Card(Long id, String title, String contents, String userId, Long columnId,
            Double order, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.columnId = columnId;
        this.order = order;
        this.createdAt = createdAt;
    }

    @Override
    public int compareTo(Card card) {
        return Double.compare(getOrder(), card.getOrder());
    }

    public Card(String title, String contents, Long columnId) {
        this.title = title;
        this.contents = contents;
        this.columnId = columnId;
    }

    public Card(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public Card(Long id, Long columnId) {
        this.id = id;
        this.columnId = columnId;
    }

    public void setNewOrder(Card upperCard, Card lowerCard) {
        if (upperCard == null && lowerCard == null) {
            order = 0.0;
            return;
        }

        if (upperCard == null) {
            order = lowerCard.getOrder() - Constant.ORDER_BASIC_DIFFERENCE;
            return;
        }

        if (lowerCard == null) {
            order = upperCard.getOrder() + Constant.ORDER_BASIC_DIFFERENCE;
            return;
        }

        order = (upperCard.getOrder() + lowerCard.getOrder()) / 2;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getUserId() {
        return userId;
    }

    public Long getColumnId() {
        return columnId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", userId='" + userId + '\'' +
                ", cardStatus=" + columnId +
                ", isDeleted=" + isDeleted +
                ", createdAt=" + createdAt +
                '}';
    }

    public Double getOrder() {
        return order;
    }

    public void initId(long id) {
        this.id = id;
    }
}
