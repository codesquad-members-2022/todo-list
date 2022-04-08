package com.example.backend.domain.card;

import java.time.LocalDateTime;

public class Card {
    private Long id;
    private String title;
    private String content;
    private CardType cardType;
    private LocalDateTime createdAt;
    private Long columnId;
    private boolean visible;
}

