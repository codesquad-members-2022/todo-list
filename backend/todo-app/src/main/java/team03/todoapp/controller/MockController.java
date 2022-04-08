package team03.todoapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team03.todoapp.controller.dto.CardResponse;
import team03.todoapp.controller.dto.CardsResponse;
import team03.todoapp.controller.dto.HistoriesResponse;
import team03.todoapp.controller.dto.HistoryResponse;

@RestController
public class MockController {

    @GetMapping("/cards")
    public CardsResponse getCards() {
        CardsResponse cardsResponse = new CardsResponse();
        CardResponse cardResponse1 = new CardResponse(1L, "코딩하기", "알고리즘도 풀자", "짱맨", 2L,
            "2022-04-06 14:55:01");
        CardResponse cardResponse2 = new CardResponse(2L, "청소하기", "청소 쓱쓱", "노리", null,
            "2022-04-06 14:55:01");
        CardResponse cardResponse3 = new CardResponse(3L, "저금하기", "티클모아", "노리", 4L,
            "2022-04-06 14:55:01");
        CardResponse cardResponse4 = new CardResponse(4L, "놀러가기", "놀러", "짱맨", null,
            "2022-04-06 14:55:01");
        CardResponse cardResponse5 = new CardResponse(5L, "쉬기", "침대에서", "노리", 6L,
            "2022-04-06 14:55:01");
        CardResponse cardResponse6 = new CardResponse(6L, "운동하기", "헬스", "짱맨", null,
            "2022-04-06 14:55:01");

        cardsResponse.putCards("todo", List.of(cardResponse1, cardResponse2));
        cardsResponse.putCards("ing", List.of(cardResponse3, cardResponse4));
        cardsResponse.putCards("done", List.of(cardResponse5, cardResponse6));

        return cardsResponse;
    }

//    @PostMapping("/card")
//    public Object add() {
//        class card_id_DTO {
//            public final int cardId = 5;
//        }
//        return new card_id_DTO();
//    }

    // @DeleteMapping("/card/{card_id}")
    public void delete(@PathVariable int card_id) {
        // Card card = jdbcRepository.findById(card_id);
    }

    @PatchMapping("/card/move/{card_id}")
    public void move() {

    }
//
//    @PatchMapping("/mock/card/{card_id}")
//    public void update() {
//
//    }

    @GetMapping("/histories")
    public HistoriesResponse getHistories() {
        HistoriesResponse historiesResponse = new HistoriesResponse();

        HistoryResponse historyResponse1 = new HistoryResponse(1L, "move", "backend-zzang", "ing",
            "done", "2020-04-07 12:00:01");
        HistoryResponse historyResponse2 = new HistoryResponse(2L, "add", "backend-zzang", "", "",
            "2022-04-07 00:00:00");
        HistoryResponse historyResponse3 = new HistoryResponse(3L, "update", "backend-zzang", "",
            "",
            "2022-04-07 12:00:11");
        HistoryResponse historyResponse4 = new HistoryResponse(4L, "move", "backend-zzang", "todo",
            "ing",
            "2022-04-07 12:05:41");
        HistoryResponse historyResponse5 = new HistoryResponse(5L, "remove", "backend-zzang", "",
            "",
            "2022-04-07 12:06:15");
        HistoryResponse historyResponse6 = new HistoryResponse(6L, "update", "backend-zzang", "",
            "",
            "2022-04-07 12:07:51");
        HistoryResponse historyResponse7 = new HistoryResponse(7L, "remove", "backend-zzang", "",
            "",
            "2022-04-07 13:00:00");
        HistoryResponse historyResponse8 = new HistoryResponse(8L, "move", "backend-zzang", "todo",
            "ing",
            "2022-04-07 14:40:06");
        HistoryResponse historyResponse9 = new HistoryResponse(9L, "add", "backend-zzang", "", "",
            "2022-04-08 12:00:01");
        HistoryResponse historyResponse10 = new HistoryResponse(10L, "remove", "backend-zzang", "",
            "",
            "2022-04-08 17:00:01");
        HistoryResponse historyResponse11 = new HistoryResponse(11L, "add", "backend-zzang", "", "",
            "2022-04-08 17:40:11");
        HistoryResponse historyResponse12 = new HistoryResponse(12L, "remove", "backend-zzang", "",
            "",
            "2022-04-08 17:50:43");
        HistoryResponse historyResponse13 = new HistoryResponse(13L, "add", "backend-zzang", "", "",
            "2022-04-09 21:11:01");
        HistoryResponse historyResponse14 = new HistoryResponse(14L, "move", "backend-zzang",
            "todo", "ing",
            "2022-04-09 21:00:02");
        HistoryResponse historyResponse15 = new HistoryResponse(15L, "remove", "backend-zzang", "",
            "",
            "2022-04-09 21:01:01");
        HistoryResponse historyResponse16 = new HistoryResponse(16L, "move", "backend-zzang", "ing",
            "todo",
            "2022-04-09 21:11:11");
        HistoryResponse historyResponse17 = new HistoryResponse(17L, "update", "backend-zzang", "",
            "",
            "2022-04-09 22:11:11");
        HistoryResponse historyResponse18 = new HistoryResponse(18L, "add", "backend-zzang", "", "",
            "2022-04-09 23:24:31");
        HistoryResponse historyResponse19 = new HistoryResponse(19L, "move", "backend-zzang", "ing",
            "done",
            "2022-04-09 23:59:53");
        HistoryResponse historyResponse20 = new HistoryResponse(20L, "update", "backend-zzang", "",
            "",
            "2022-04-09 23:59:59");
        HistoryResponse historyResponse21 = new HistoryResponse(21L, "move", "backend-zzang",
            "done", "todo",
            "2022-04-09 24:00:00");

        historiesResponse.addHistory(historyResponse1);
        historiesResponse.addHistory(historyResponse2);
        historiesResponse.addHistory(historyResponse3);
        historiesResponse.addHistory(historyResponse4);
        historiesResponse.addHistory(historyResponse5);
        historiesResponse.addHistory(historyResponse6);
        historiesResponse.addHistory(historyResponse7);
        historiesResponse.addHistory(historyResponse8);
        historiesResponse.addHistory(historyResponse9);
        historiesResponse.addHistory(historyResponse10);
        historiesResponse.addHistory(historyResponse11);
        historiesResponse.addHistory(historyResponse12);
        historiesResponse.addHistory(historyResponse13);
        historiesResponse.addHistory(historyResponse14);
        historiesResponse.addHistory(historyResponse15);
        historiesResponse.addHistory(historyResponse16);
        historiesResponse.addHistory(historyResponse17);
        historiesResponse.addHistory(historyResponse18);
        historiesResponse.addHistory(historyResponse19);
        historiesResponse.addHistory(historyResponse20);
        historiesResponse.addHistory(historyResponse21);

        return historiesResponse;
    }


}
