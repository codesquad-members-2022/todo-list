package com.hooria.todo.dto;

import com.hooria.todo.domain.Card;
import lombok.Getter;

@Getter
public class AddCardParam {

    private String status;
    private String title;
    private String content;
    private String userId;
    private String device;

    // TODO : 리아코와 상의 후 빈생성자, 어노테이션 추가 중 어떤 방법을 사용할지 정하기
    public AddCardParam() {
    }

    public AddCardParam(String status, String title, String content, String userId,
        String device) {
        this.status = status;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.device = device;
    }

    public Card toEntity() {
        return Card.of(status, title, content, userId, device, 0);
    }
}
