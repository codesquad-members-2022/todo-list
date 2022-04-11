package com.ijava.todolist.history.util;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.controller.dto.HistoryResponse;
import com.ijava.todolist.history.domain.History;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HistoryResolver {

    public static List<HistoryResponse> resolveHistoryList(List<History> historyList) {

        // 각 카드의 이전 컬럼 위치를 저장하기 위한 HashMap
        // MOVE 행동의 경우, 이전 컬럼 위치가 필요하여 생성
        // <K> - cardId
        // <V> - recentColumnId
        HashMap<Long, Long> historyHashMap = new HashMap<>();
        List<HistoryResponse> historyResponseList = new ArrayList<>();

        for (History history : historyList) {
            HistoryResponse historyResponse = makeHistoryResponse(history, historyHashMap);
            historyResponseList.add(historyResponse);
        }
        Collections.reverse(historyResponseList);
        return historyResponseList;
    }


    private static HistoryResponse makeHistoryResponse(History history,
        HashMap<Long, Long> historyHashMap) {
        Action action = history.getAction();
        Long cardId = history.getCardId();
        Long oldColumId = historyHashMap.get(cardId);
        Long newColumnId = history.getColumnsId();

        historyHashMap.put(cardId, newColumnId);
        if (action == Action.MOVE) {
            return new HistoryResponse(history, oldColumId);
        }
        return new HistoryResponse(history);
    }
}
