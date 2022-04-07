package com.example.backend.controller.card.dto;

public class Author {

    private String userAgent;

    public Author(String userAgent){
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "userAgent='" + userAgent + '\'' +
                '}';
    }
}
