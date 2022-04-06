package com.todolist.domain;

import java.time.LocalDateTime;

public class Card {

    private Integer cardId;                     // 카드 고유 번호 (PK)
    private String cardTitle;                   // 카드 제목
    private String cardContent;                 // 카드 내용
    private LocalDateTime cardAddDateTime;      // 생성 시간
    private LocalDateTime cardUpdateDateTime;
    private Integer internalOrder;              // 보드 내부의 카드 순서
    private String boardName;
}
