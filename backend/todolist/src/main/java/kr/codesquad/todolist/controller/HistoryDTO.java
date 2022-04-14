package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.domain.History;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class HistoryDTO {

    private Long id;
    private String action;
    private String cardSubject;
    private String prevCardStatus;
    private String currentCardStatus;
    private String createdAt;
    private Long userId;
    private Long cardId;

    public HistoryDTO (History history) {
        this.id = history.getCardId();
        this.action = history.getAction().name();
        this.cardSubject = history.getCardSubject();
        this.prevCardStatus = history.getPrevCardStatus().getText();
        this.currentCardStatus = history.getCurrentCardStatus().getText();
        this.createdAt = history.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.userId = history.getUserId();
        this.cardId = history.getCardId();
    }

    @Data
    public static class Response {

        private final List<HistoryDTO> histories;

        public Response(List<HistoryDTO> histories) {
            this.histories = histories;
        }
    }
}
