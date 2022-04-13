package com.todolist.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

    private static final String FIXED_USER_ID = "1";

    @GetMapping("/user")
    public ResponseEntity<Void> getUserId(HttpServletResponse response) {
        Cookie cookie = new Cookie("userId", FIXED_USER_ID);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
