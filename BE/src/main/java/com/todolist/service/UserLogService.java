package com.todolist.service;

import com.todolist.domain.UserLog;
import com.todolist.dto.UserLogDto;
import com.todolist.dto.UserLogListDto;
import com.todolist.repository.UserLogRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserLogService {

    private final UserLogRepository userLogRepository;

    public UserLogService(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    public UserLogListDto getWorkLogList(String userId) {
        List<UserLogDto> userLogDtoList = userLogRepository.findAllByUserId(userId)
            .stream().map(UserLog::convertToDto).collect(Collectors.toList());

        return new UserLogListDto(userId, userLogDtoList);
    }
}
