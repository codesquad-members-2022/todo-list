package com.ijava.todolist.user.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class User {

    private Long id;
    private final String name;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
}
