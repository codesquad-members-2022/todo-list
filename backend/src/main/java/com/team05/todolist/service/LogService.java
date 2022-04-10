package com.team05.todolist.service;

import com.team05.todolist.domain.Event;
import com.team05.todolist.domain.Log;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.repository.LogRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public LogDTO save(Event event, String cardTitle, String section) {
        Log log = new Log(event, LocalDateTime.now(), cardTitle, null, section);
        Integer logId = logRepository.save(log);

        return new LogDTO(logId, log.getEventType(), log.getLogTime(), log.getTitle(), log.getPrevSection(),
            log.getSectionType());
    }

}
