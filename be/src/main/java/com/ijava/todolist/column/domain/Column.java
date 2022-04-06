package com.ijava.todolist.column.domain;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode
@ToString
public class Column {

    private final Long id;
    private final String name;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
}
