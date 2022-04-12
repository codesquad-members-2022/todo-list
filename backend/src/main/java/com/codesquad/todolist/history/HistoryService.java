package com.codesquad.todolist.history;

import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
import com.codesquad.todolist.history.dto.HistoryResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ModifiedFieldRepository modifiedFieldRepository;

    public HistoryService(HistoryRepository historyRepository,
        ModifiedFieldRepository modifiedFieldRepository) {
        this.historyRepository = historyRepository;
        this.modifiedFieldRepository = modifiedFieldRepository;
    }

    public List<HistoryResponse> findAll() {
        List<History> histories = historyRepository.findAll();

        List<Integer> historyIds = histories.stream()
            .map(History::getHistoryId)
            .collect(Collectors.toList());

        List<ModifiedField> modifiedFields = modifiedFieldRepository.findByHistoryIds(historyIds);

        Map<Integer, List<ModifiedField>> modifiedFieldMap = modifiedFields.stream()
            .collect(Collectors.groupingBy(ModifiedField::getHistoryId));

        histories.forEach(
            history -> history.setFields(modifiedFieldMap.get(history.getHistoryId()))
        );

        return histories.stream()
            .map(HistoryResponse::from)
            .collect(Collectors.toList());
    }

}
