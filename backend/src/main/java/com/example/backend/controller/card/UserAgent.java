package com.example.backend.controller.card;

public class UserAgent {

    private String userAgent;

    public UserAgent(String userAgent){
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
