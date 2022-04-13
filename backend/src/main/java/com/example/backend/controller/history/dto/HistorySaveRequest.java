package com.example.backend.controller.history.dto;

import com.example.backend.domain.history.Action;
import com.example.backend.domain.history.History;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HistorySaveRequest {

    @NotBlank(message = "내용을 반드시 입력해야 합니다.")
    private String content;
    @NotNull(message = "작성자는 반드시 입력해야 합니다.")
    private String author;
    @NotNull(message = "행동은 반드시 입력해야 합니다.")
    private Action action;
    @NotNull(message = "폰트는 반드시 입력해야 합니다.")
    private String font;
    @NotNull(message = "회원 번호를 반드시 입력해야 합니다.")
    private Long memberId;
    private Long cardId;
    private LocalDateTime createdAt;
    private boolean visible;

    public HistorySaveRequest() {
    }

    public HistorySaveRequest(String content, String author, Action action, String font, Long memberId, Long cardId) {
        this.content = content;
        this.author = author;
        this.action = action;
        this.font = font;
        this.memberId = memberId;
        this.cardId = cardId;
        this.createdAt = LocalDateTime.now();
        this.visible = true;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Action getAction() {
        return action;
    }

    public String getFont() {
        return font;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getCardId() {
        return cardId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isVisible() {
        return visible;
    }

    public History toEntity() {
        return new History(this.content, this.action, this.memberId, this.cardId);
    }
}
