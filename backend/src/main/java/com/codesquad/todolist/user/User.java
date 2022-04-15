package com.codesquad.todolist.user;

import org.springframework.web.multipart.MultipartFile;

public class User {

    private Integer userId;
    private String userName;
    private Boolean deleted;

    public User(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
