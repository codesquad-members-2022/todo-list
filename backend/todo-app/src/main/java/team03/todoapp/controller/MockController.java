package team03.todoapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/card")
    public Object add() {
        class card_id_DTO {
            public final int cardId = 5;
        }
        return new card_id_DTO();
    }

    @DeleteMapping("/card/{card_id}")
    public void delete(@PathVariable int card_id) {
        // Card card = jdbcRepository.findById(card_id);
    }

    @PatchMapping("/card/move/{card_id}")
    public void move() {

    }

    @PatchMapping("/card/{card_id}")
    public void update() {

    }

    @GetMapping("/histories")
    public HistoriesResponse getHistories() {
        HistoriesResponse historiesResponse = new HistoriesResponse();
        HistoryResponse historyResponse1 = new HistoryResponse(1L, "move", "android-zzang", "ing", "done", "2020-04-07 12:00:01");
        HistoryResponse historyResponse2 = new HistoryResponse(2L, "add", "backend-zzang", "", "", "2022-04-07 12:00:01");

        historiesResponse.addHistory(historyResponse1);
        historiesResponse.addHistory(historyResponse2);
        return historiesResponse;
    }


}
