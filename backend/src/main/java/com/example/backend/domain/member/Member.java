package com.example.backend.domain.member;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String nickName;
    private String memberLoginId;
    private String password;
    private LocalDateTime createdAt;
    private boolean visible;
    private String imageUrl;
}
