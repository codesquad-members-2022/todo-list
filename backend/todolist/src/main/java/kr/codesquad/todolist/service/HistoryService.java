package kr.codesquad.todolist.service;

import kr.codesquad.todolist.controller.HistoryDTO;
import kr.codesquad.todolist.dao.HistoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryDao historyDao;

    @Transactional(readOnly = true)
    public HistoryDTO.Response readAllFrom(Long userId) {
        return new HistoryDTO.Response(
                historyDao.findAllByUserId(userId)
                .stream()
                .map(HistoryDTO::new)
                .collect(Collectors.toList()));
    }
}
