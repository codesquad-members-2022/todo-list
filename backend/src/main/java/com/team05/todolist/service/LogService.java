package com.team05.todolist.service;

import com.team05.todolist.domain.Event;
import com.team05.todolist.domain.Log;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.repository.LogRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public LogDTO save(Event event, String cardTitle, String section) {
        return this.save(event, cardTitle, null, section);
    }

    public LogDTO save(Event event, String cardTitle, String prevSection, String section) {
        Log log = new Log(event.getEventType(), LocalDateTime.now(), cardTitle, prevSection, section);
        Integer logId = logRepository.save(log);

        return new LogDTO(logId, log.getEventType(), log.getLogTime(), log.getTitle(), log.getPrevSection(),
            log.getSectionType());
    }

    public List<LogDTO> findLogs() {
        List<Log> logs = logRepository.findAll();
        List<LogDTO> logDtos = new ArrayList<>();
        for (Log log : logs) { // log를 10개씩 출력으로 변경해야 한다.
            logDtos.add(new LogDTO(log.getId(), log.getEventType(), log.getLogTime(),
                    log.getTitle(), log.getPrevSection(), log.getSectionType()));
        }
        return logDtos;
    }

    public LogDTO delete(Event event, String cardTitle, String section) {
        Log log = new Log(event.getEventType(), LocalDateTime.now(), cardTitle, section);
        Integer logId = logRepository.save(log);
        return new LogDTO(logId, log.getEventType(), log.getLogTime(), log.getTitle(), log.getSectionType());
    }
}
