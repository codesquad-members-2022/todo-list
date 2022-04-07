package team03.todoapp.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class HistoriesResponse {

    private List<HistoryResponse> histories = new ArrayList<>();

    public List<HistoryResponse> getHistories() {
        return histories;
    }

    public void addHistory(HistoryResponse historyResponse) {
        histories.add(historyResponse);
    }

}
