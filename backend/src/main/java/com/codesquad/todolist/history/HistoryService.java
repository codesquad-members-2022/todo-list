package com.codesquad.todolist.history;

import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
import com.codesquad.todolist.history.dto.HistoryResponse;
import com.codesquad.todolist.util.page.Criteria;
import com.codesquad.todolist.util.page.Slice;
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

    public Slice<HistoryResponse> findAll(Criteria criteria) {
        // 페이지 내에 포함된 모든 history 객체를 조회
        List<History> histories = historyRepository.findAll(criteria);

        // 조회한 histories 리스트에서 history Id 속성만 리스트로 추출
        List<Integer> historyIds = histories.stream()
            .map(History::getHistoryId)
            .collect(Collectors.toList());

        // history Id 리스트에 포함된 history Id 가 FK 인 modified field 테이블 조회
        List<ModifiedField> modifiedFields = modifiedFieldRepository.findByHistoryIds(historyIds);

        // map 자료구조에 history Id 별로 modified field 리스트를 저장
        Map<Integer, List<ModifiedField>> modifiedFieldMap = modifiedFields.stream()
            .collect(Collectors.groupingBy(ModifiedField::getHistoryId));

        // 각 history 객체에 조회된 modified field 리스트를 설정
        histories.forEach(
            history -> history.setFields(modifiedFieldMap.get(history.getHistoryId()))
        );

        List<HistoryResponse> historyResponses = histories.stream()
            .map(HistoryResponse::from)
            .collect(Collectors.toList());
        return new Slice<>(historyResponses, criteria);
    }

}
