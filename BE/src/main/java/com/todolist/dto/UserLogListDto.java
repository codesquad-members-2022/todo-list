package com.todolist.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class UserLogListDto {

    private String userId;
    private List<UserLogDto> workLogList;

    public UserLogListDto(String userId, List<UserLogDto> workLogList) {
        this.userId = userId;
        this.workLogList = workLogList;
    }
}
